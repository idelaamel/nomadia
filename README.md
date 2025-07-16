# TouristeProject

A Spring Boot application for tourism management with features for activities, locations, accessibility services, and user management.

## Features

- **Activity Management**: Create and manage tourist activities
- **Location Services**: Handle tourist locations and attractions
- **Accessibility Services**: Manage accessibility features for inclusive tourism
- **User Management**: User registration, authentication, and profile management
- **Google OAuth Integration**: Sign in with Google
- **Email Verification**: Email-based user verification
- **File Upload**: Support for image and document uploads
- **RESTful API**: Well-documented API with Swagger/OpenAPI

## Technologies Used

- **Backend**: Spring Boot 3.x, Java 17
- **Database**: MySQL (development), PostgreSQL (production)
- **Security**: Spring Security with OAuth2
- **Documentation**: SpringDoc OpenAPI (Swagger)
- **Build Tool**: Maven
- **Email**: SMTP integration

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL (for local development)

### Local Development

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/TouristeProject.git
   cd TouristeProject
   ```

2. Configure your database in `src/main/resources/application.properties`

3. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

4. Access the application:
   - Application: http://localhost:8080
   - API Documentation: http://localhost:8080/swagger-ui.html

### Production Deployment

The application is configured for deployment on Railway, Render, or Heroku with PostgreSQL.

## Configuration

### Environment Variables (Production)

- `DATABASE_URL`: PostgreSQL connection string
- `GOOGLE_CLIENT_ID`: Google OAuth client ID
- `GOOGLE_CLIENT_SECRET`: Google OAuth client secret
- `GOOGLE_REDIRECT_URI`: OAuth redirect URI for your domain
- `MAIL_HOST`: SMTP host for email service
- `MAIL_USERNAME`: SMTP username
- `MAIL_PASSWORD`: SMTP password

## API Documentation

Once the application is running, visit `/swagger-ui.html` for interactive API documentation.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.
