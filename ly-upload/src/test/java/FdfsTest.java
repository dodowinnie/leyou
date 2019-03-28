import com.brandon.leyou.upload.UploadApplication;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UploadApplication.class)
public class FdfsTest {
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @Test
    public void testUpload() throws FileNotFoundException {
        File file = new File("D:\\opt\\leyou\\rose.jpg");
        StorePath storePath = storageClient.uploadFile(new FileInputStream(file), file.length(), "jpg", null);
        System.out.println(storePath.getFullPath());
        System.out.println(storePath.getPath());
    }

    @Test
    public void testUploadAndThumb() throws FileNotFoundException {
        File file = new File("D:\\opt\\leyou\\ac_dc.jpg");
        StorePath storePath = storageClient.uploadFile(new FileInputStream(file), file.length(), "jpg", null);
        System.out.println(storePath.getFullPath());
        System.out.println(storePath.getPath());
        String thumbImagePath = thumbImageConfig.getThumbImagePath(storePath.getPath());
        System.out.println(thumbImagePath);
    }

}
