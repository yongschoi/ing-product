package yongs.temp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import yongs.temp.config.MinioConfig;

@Service
public class MinioService {	
	private static final String MINIO_ROOT_USER = "minioadmin";
	private static final String MINIO_ROOT_PASSWORD = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
	
	private MinioClient minioClient;
	
	@Autowired
    public MinioService(MinioConfig config) {
		minioClient = MinioClient.builder()
				.endpoint("http://" + config.getHost() + ":" + config.getPort())
				.credentials(MINIO_ROOT_USER, MINIO_ROOT_PASSWORD)
				.build();
    }
	
	public void putObject(String bucket, String name, MultipartFile file) throws Exception {
		minioClient.putObject(PutObjectArgs.builder()
				.bucket(bucket)
				.object(name)
				.stream(file.getInputStream(), file.getSize(), -1)
				.contentType(file.getContentType())
				.build());
	}
	
	public void removeObject(String bucket, String name) throws Exception {
		minioClient.removeObject(RemoveObjectArgs.builder()
				.bucket(bucket)
				.object(name)
				.build());
	}
	
	public String getObjectUrl(String bucket, String name) {
		String url = null;
		try {
			url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
				      .method(Method.GET)
				      .bucket(bucket)
				      .object(name)
				      .build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
}
