# Media Converter 

This application provides a REST API for file conversion, allowing transformation of audio and video files between various formats using a JAVE2-based service.

## About

Provide a detailed introduction to your project here. Explain the problem it solves, the technologies used, and any other important details.

## Installation


  **1. Clone the repository:**
  
    git clone git@github.com:yevhenpi/media-converter.git
  **2. Configure .env.example file and rename it to .env**

## Usage

 Explain how to run and use your project. Provide code examples or screenshots if needed.
 Running the Application



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

    curl -X POST "http://localhost:8080/api/v1/upload/audio" \
         -H "Content-Type: application/json" \
         -H "Authorization: Bearer YOUR_TOKEN" \
         -d {"fileName": "sample.mp3", "fileSize": "1829938"}

Example Response:

    {
      "url": PRESIGNED_URL
    }

(Repeat the above structure for additional endpoints as needed)
## Docker Compose Setup

This project uses Docker Compose to manage containerized services.

### Prerequisites

Docker installed on your system.
Docker Compose installed.

### Running with Docker Compose

Build and start the containers:

    docker compose up --build

Stop the containers:

    docker compose down



Features

List the main features or functionalities of your project:

    Feature 1: Description.
    Feature 2: Description.
    Feature 3: Description.

## Contributing

Contributions are welcome! To contribute:

    Fork the repository.
    Create a new branch (git checkout -b feature/your-feature).
    Make your changes and commit them (git commit -m 'Add new feature').
    Push to the branch (git push origin feature/your-feature).
    Open a Pull Request.

Please update tests and documentation as appropriate.
License

Distributed under the MIT License. See the LICENSE file for more details.

## Contact


    yevhen.pidopryhora@gmail.com
  

## Credits

 Conversion service is based on JAVE2 library by a-schild.

 https://github.com/a-schild/jave2



