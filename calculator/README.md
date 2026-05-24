# Simple Calculator Application

A modern web-based Java calculator application with REST API backend, designed for practicing DevOps concepts.

## Features

- **Web UI**: Modern, responsive HTML/CSS/JavaScript interface
- **REST API**: Complete calculator API endpoints
- **Basic Operations**: Addition, Subtraction, Multiplication, Division
- **Advanced Operations**: Square Root, Power
- **Error Handling**: Comprehensive error handling and validation
- **Keyboard Support**: Full keyboard input support
- **Health Checks**: Built-in health endpoint for monitoring
- **Responsive Design**: Works on desktop, tablet, and mobile

## Project Structure

```
calculator/
├── src/
│   ├── main/
│   │   ├── java/com/calculator/
│   │   │   ├── Calculator.java              # Core calculator logic
│   │   │   ├── CalculatorWebApplication.java # Spring Boot entry point
│   │   │   └── CalculatorController.java    # REST API endpoints
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── index.html               # Web UI
│   │       │   ├── styles.css               # Styling
│   │       │   └── script.js                # Frontend logic
│   │       └── application.properties       # Spring configuration
│   └── test/java/com/calculator/
│       └── CalculatorTest.java              # Unit tests
├── pom.xml                                   # Maven configuration
├── Dockerfile                                # Docker container definition
├── docker-compose.yml                        # Docker Compose setup
├── .gitignore                                # Git ignore rules
└── README.md                                 # This file
```

## Building the Application

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher

### Build Steps

```bash
cd calculator
mvn clean package
```

This will create an executable JAR file at `target/calculator-app.jar`

## Running the Application

### Web UI (Default)
```bash
java -jar target/calculator-app-1.0.0.jar
```
Then open your browser and navigate to: `http://localhost:8080`

The web UI provides:
- 🖱️ Click buttons for numbers and operations
- ⌨️ Full keyboard support (0-9, +, -, *, /, =, Backspace, Escape)
- 📱 Responsive design for all devices
- 📊 Operation history display

### Menu Mode (CLI)
To run the command-line version, use the legacy `CalculatorApp`:
```bash
# First, you'll need to compile just that class or create a separate entry point
```

### Interactive Mode (CLI)
```bash
# Interactive command-line interface (if configured)
```

## API Endpoints

The application provides REST API endpoints for calculator operations:

### Addition
```bash
curl -X POST "http://localhost:8080/api/calculator/add?a=5&b=3"
```
Response: `{"operation":"+","operand1":5,"operand2":3,"result":8}`

### Subtraction
```bash
curl -X POST "http://localhost:8080/api/calculator/subtract?a=10&b=4"
```

### Multiplication
```bash
curl -X POST "http://localhost:8080/api/calculator/multiply?a=6&b=7"
```

### Division
```bash
curl -X POST "http://localhost:8080/api/calculator/divide?a=20&b=4"
```

### Square Root
```bash
curl -X POST "http://localhost:8080/api/calculator/sqrt?a=16"
```

### Power
```bash
curl -X POST "http://localhost:8080/api/calculator/power?base=2&exponent=8"
```

### Health Check
```bash
curl "http://localhost:8080/api/calculator/health"
```

## Running Tests

```bash
mvn test
```

## Docker Deployment

### Build Docker Image
```bash
docker build -t calculator-app:1.0.0 .
```

### Run Docker Container
```bash
docker run -p 8080:8080 calculator-app:1.0.0
```
Then access the application at: `http://localhost:8080`

### Using Docker Compose
```bash
docker-compose up --build
```
This will:
- Build the image
- Start the container with port 8080 exposed
- Enable health checks
- Set memory limits

To stop:
```bash
docker-compose down
```


## DevOps Practice Areas

This web application is ideal for practicing:

1. **Build Automation**: Maven build pipeline with Spring Boot
2. **Containerization**: Multi-stage Docker builds with optimized images
3. **Container Orchestration**: Docker Compose for service management
4. **CI/CD Pipelines**: GitHub Actions workflow for automated builds
5. **API Development**: REST API with proper error handling
6. **Frontend Development**: Modern responsive web UI
7. **Testing**: Unit tests with JUnit and Spring Test
8. **Security**: Non-root user, CORS handling, health checks
9. **Monitoring**: Health endpoints and metrics
10. **Infrastructure as Code**: Docker and Docker Compose files
11. **Port Management**: Service exposure and networking
12. **Configuration Management**: Application properties file

## API Reference

### REST Endpoints

The CalculatorController provides these endpoints:

```
POST /api/calculator/add?a=<number>&b=<number>
POST /api/calculator/subtract?a=<number>&b=<number>
POST /api/calculator/multiply?a=<number>&b=<number>
POST /api/calculator/divide?a=<number>&b=<number>
POST /api/calculator/sqrt?a=<number>
POST /api/calculator/power?base=<number>&exponent=<number>
GET  /api/calculator/health
```

## Error Handling

- **Division by Zero**: Throws `IllegalArgumentException`
- **Negative Square Root**: Throws `IllegalArgumentException`
- **Invalid Input**: Prompts for valid numeric input

## License

MIT License - Free to use for educational and DevOps practice purposes.

## Author

Created for DevOps learning and practice.
