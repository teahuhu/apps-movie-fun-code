package org.superbiz.moviefun.blobstore;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class S3Store implements BlobStore{

    private final AmazonS3Client s3Client;
    private final String s3BucketName;

    public S3Store(AmazonS3Client s3Client, String s3BucketName) {
        this.s3Client = s3Client;
        this.s3BucketName = s3BucketName;
    }


    @Override
    public void put(Blob blob) throws IOException {
        ObjectMetadata metaData = new ObjectMetadata();
        s3Client.putObject(s3BucketName, blob.name, blob.inputStream, metaData);

    }

    @Override
    public Optional<Blob> get (String name) throws IOException {

        InputStream inputStream = s3Client.getObject(s3BucketName, name).getObjectContent();
        return Optional.of(new Blob(name, inputStream, new Tika().detect(new File(name))));

    }

    @Override
    public void deleteAll () {
        // ...
    }
}
