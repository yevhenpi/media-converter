# Media Converter 

This application provides a REST API for file conversion, allowing transformation of audio and video files between various formats using a JAVE2-based service.

## About

The main goal of this pet project is to deepen  understanding of software development,
explore microservice architecture,
and gain hands-on experience in solving real-world problems in a close to real-world environment. 
At this stage only audio file conversion is functional.

App is proven to work on Ubuntu 22.04 LTS and Orange Pi 1.0.4 Jammy.



## Requirements
AWS Setup Requirements

This application requires some AWS resources to be configured before you can fully run it.
Please ensure the following components are set up:

AWS IAM User:
Create an IAM user with the necessary permissions to interact with S3 and SQS. This user should have policies that allow read/write access to the S3 buckets and permission to send and receive messages via SQS.

Two S3 Buckets:

Upload Bucket: This bucket is used to receive files.

Secondary Bucket: This bucket is used for additional storage or processing as required by the application.
Make sure both buckets have the appropriate policies to grant the IAM user access.

Event Notifications:
Configure an event notification on the Upload Bucket so that whenever a file is uploaded, an event is triggered. This event should send a message to an SQS queue, which the application monitors for processing new uploads.

Ensure that all these AWS configurations are correctly set up to allow the application to function as intended. This setup will enable the application to process file uploads automatically and securely.
## Installation
1.Install openjdk-21 and verify installation:


    sudo apt install openjdk-21-jdk
...

    java --version



2.Install maven or check if it is already installed:

    sudo apt install maven
   
   ...

     mvn -v

3.Install docker or make sure its installed:


    sudo apt install docker.io

...



    docker compose version

4.Clone the repository:


    git clone git@github.com:yevhenpi/media-converter.git  


5.Configure .env.example files and rename them to .env

6.Build with maven and pray:


    mvn install

7.You can run application with:

    docker compose --profile prod up



## Usage

 
Workflow looks like this: 
1. Get JWT token from /auth/login endpoint.
2. Send POST request with file metadata to /upload endpoint and receive presigned URL.
3. Upload actual file with PUT request using acquired URL. 
4. Send POST request with conversion metadata to /jobs/convert endpoint and receive JobID.
5. If job status on /jobs/status endpoint is DONE, get download URL from /jobs/download endpoint.

More detailed endpoint documentation is down below.




## API Endpoints

Using --insecure flag because working with self-signed ssl certificate in dev environment.

### Files endpoint

POST for getting upload url.

Example Request:

    curl --insecure -X POST "https://localhost:8443/api/v1/files" \
          -H "Content-Type: application/json" \
          -H "Authorization: Bearer YOUR_TOKEN" \
          -d '{"fileName": "sample.mp3", "fileSize": "1829938"}'

Example Response:

    {
      "url": "PRESIGNED_URL"
    }

GET for getting list of uploaded files

Example Request:

    curl --insecure -X GET "https://localhost:8443/api/v1/files" \
          -H "Authorization: Bearer YOUR_TOKEN" 

Example Response:

    {
      sample.mp3,
      sample2.wav
    }

### Jobs endpoint

This endpoint includes both required fields and optional fields.
For a better understanding of the value requirements for the optional fields,
please refer to the official JAVE documentation:

https://www.sauronsoftware.it/projects/jave/manual.php

Example request with required fields only:

    curl --insecure -X POST "https://localhost:8443/api/v1/jobs/audio" \
          -H "Content-Type: application/json" \
          -H "Authorization: Bearer YOUR_TOKEN" \
          -d '{"fileName": "sample.mp3", "outputFormat": "wav"}'

Example request with required fields and ALL optional fields:

    curl --insecure -X POST "https://localhost:8443/api/v1/jobs/audio" \
          -H "Content-Type: application/json" \
          -H "Authorization: Bearer YOUR_TOKEN" \
          -d '{
               "fileName": "sample.mp3", 
               "outputFormat": "wav",
               "codec": "libmp3lame", 
               "samplingRate": "44100",
               "bitRate": "128000", 
               "channels": "2",
               "volume": "256"
              }'

Example Response:

    {
      "message":"file is being processed",
      "jobId": "11uh3u-kdkdjd-ifosi1"
    }
### Status endpoint 
This endpoint (/api/v1/jobs/status) accepts a JSON array in the request body containing job IDs. 
The behavior varies based on the contents of the array:

Single Job ID:

If the array contains one job ID (e.g., ["job1"]), the response will include the status for that specific job.

Multiple Job IDs:

If the array contains two or more job IDs (e.g., ["job1", "job2"]), the response will include the statuses for all the provided jobs.

Empty Array:

If the array is empty (e.g., []), the response will return a list of all jobs for this specific user.

Example requests:

    curl --insecure -X POST "https://localhost:8443/api/v1/jobs/status" \
          -H "Content-Type: application/json" \
          -H "Authorization: Bearer YOUR_TOKEN" \
          -d '{"jobIds": [JOB_ID_1, JOB_ID_2, JOB_ID_3]}'
Example response:

    [
    {
        "jobId": "JOB_ID_1",
        "jobStatus": "DONE"
    },
    {
        "jobId": "JOB_ID_2",
        "jobStatus": "PROCESSING"
    }
    {
        "jobId": "JOB_ID_3",
        "jobStatus": "FAILED"
    }
    ]



### Download endpoint

Example request:

     curl --insecure -X GET "https://localhost:8443/api/v1/download?jobId=JOB_ID_1" \
           -H "Authorization: Bearer YOUR_TOKEN" 

Example response:

    {
        "url": "URL",
        "jobId": "JOB_ID_1"
    }

### Authentication endpoint

For now this endpoint is underdeveloped so no registration available, only test accounts.
Test credentials are provided in example below. 

    curl --insecure -X POST "https://localhost:8443/auth/login" \
          -H "Content-Type: application/json" \
          -d '{"email": "admin@gmail.com", "password": "test"}'

### Info endpoints

GET list of codecs:

    /api/v1/encoders/audio
  
GET list of supported audio format extensions:
     
    /api/v1/formats/audio




## Contributing

Thank you for your interest in contributing to this project!
At this early stage, I am developing the project independently until the first stable release.
While you are welcome to fork the repository and build upon it,
please note that I will not be accepting pull requests to the main branch at this time.

Feel free to experiment and use the project as a starting point for your own ideas.



## Contact


    yevhen.pidopryhora@gmail.com
  

## Credits

 Conversion service is based on JAVE2 library by a-schild.

 https://github.com/a-schild/jave2

## License
This project is licensed under the GNU General Public License v3.0 - see the [LICENSE](LICENSE) file for details.



