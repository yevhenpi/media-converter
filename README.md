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

6.Install with maven and pray:


    mvn install

7.You can run application with:

    docker compose --profile prod up



## Usage

 
Workflow looks like this: 
1. Send POST request with file metadata to /upload endpoint and receive presigned URL.
2. Upload actual file with PUT request using acquired URL. 
3. Send POST request with conversion metadata to /jobs/convert endpoint and receive JobID.
4. If job status on /jobs/status endpoint is DONE, get download URL from /jobs/download endpoint.

More detailed endpoint documentation is down below.




## API Endpoints

Below is a list of the main API endpoints. For each endpoint, provide a brief description, the HTTP method, required headers, parameters, and example request/response.

### Upload endpoint

    URL: /api/v1/upload/audio
    Method: POST
    Headers:
        Content-Type: application/json
        Authorization: Bearer YOUR_TOKEN
    Request Body:
    {
     "fileName": "sample.mp3",
     "fileSize": "1829938"
    }

Example Request:

    curl --insecure -X POST "https://localhost:8443/api/v1/upload/audio" \
         -H "Content-Type: application/json" \
         -H "Authorization: Bearer YOUR_TOKEN" \
         -d '{"fileName": "sample.mp3", "fileSize": "1829938"}'

Example Response:

    {
      "url": PRESIGNED_URL
    }

...

    curl --insecure -X GET "https://localhost:8443/api/v1/files" \
      -H "Content-Type: application/json" \
      -H "Authorization: Bearer YOUR_TOKEN" 




Features

List the main features or functionalities of your project:

    Feature 1: Description.
    Feature 2: Description.
    Feature 3: Description.

## Contributing

Thank you for your interest in contributing to this project!
At this early stage, I am developing the project independently until the first stable release.
While you are welcome to fork the repository and build upon it,
please note that I will not be accepting pull requests to the main branch at this time.

Feel free to experiment and use the project as a starting point for your own ideas.
I appreciate your understanding and support,
and I look forward to sharing future updates as the project matures.


## Contact


    yevhen.pidopryhora@gmail.com
  

## Credits

 Conversion service is based on JAVE2 library by a-schild.

 https://github.com/a-schild/jave2



