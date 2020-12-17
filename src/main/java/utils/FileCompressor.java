package utils;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class FileCompressor {
  public void makeCompress(String srcFile) throws IOException {
    createTarGz(srcFile + ".tar.gz", srcFile);
  }

  private void createTarGz(String outFileName, String... fileNames) throws IOException {
    try (FileOutputStream fOut = new FileOutputStream(new File(outFileName));
         BufferedOutputStream bOut = new BufferedOutputStream(fOut);
         GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(bOut);
         TarArchiveOutputStream tOut = new TarArchiveOutputStream(gzOut)) {

      for (String fileName : fileNames) {
        File source = new File(fileName);

        System.out.println("Adding File: "
            + source.getParentFile().toURI()
            .relativize(source.toURI()).getPath());

        TarArchiveEntry entry = new TarArchiveEntry(source, source
            .getParentFile().toURI().relativize(source.toURI())
            .getPath());

        tOut.putArchiveEntry(entry);

        try (FileInputStream fi = new FileInputStream(source);
             BufferedInputStream sourceStream = new BufferedInputStream(fi, 2048)) {
          int count;
          byte data[] = new byte[2048];
          while ((count = sourceStream.read(data, 0, 2048)) != -1) {
            tOut.write(data, 0, count);
          }
        }
        tOut.closeArchiveEntry();
      }
    }
    System.out.println("tar.gz file created successfully!!");
  }

  public void extract(String inputFile) {
    File file = new File(inputFile);
    try (InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
      TarArchiveInputStream tis = new TarArchiveInputStream(new GZIPInputStream(inputStream))) {
      TarArchiveEntry entry;
      while ((entry = (TarArchiveEntry) tis.getNextEntry()) != null) {
        if (!entry.isDirectory()) {
          try (FileOutputStream fos = new FileOutputStream(file.getParentFile().getAbsolutePath() + "/" + entry.getName(), false);
               BufferedOutputStream dest = new BufferedOutputStream(fos)) {
            IOUtils.copy(tis, dest);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
