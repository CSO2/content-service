# 📝 Content Service

> Manages blog posts, FAQs, and testimonials for the CS02 E-Commerce Platform

## 📋 Overview

The Content Service handles all content management operations including blog posts, frequently asked questions (FAQs), and customer testimonials. It provides a CMS-like functionality for the platform's marketing and informational content.

## 🛠️ Technology Stack

| Component | Technology | Version |
|-----------|------------|---------|
| Language | Java | 17 |
| Framework | Spring Boot | 4.0.0 |
| Database | MongoDB | Latest |
| Build Tool | Maven | 3.x |
| Documentation | Lombok | Latest |

## 🚀 API Endpoints

### Blog Posts

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| `GET` | `/api/content/blog` | No | Get all published blog posts |
| `GET` | `/api/content/blog?featured=true` | No | Get featured blog posts |
| `GET` | `/api/content/blog/slug/{slug}` | No | Get blog post by URL slug |
| `GET` | `/api/content/blog/{id}` | No | Get blog post by ID |
| `POST` | `/api/content/blog` | Admin | Create new blog post |
| `PUT` | `/api/content/blog/{id}` | Admin | Update blog post |
| `DELETE` | `/api/content/blog/{id}` | Admin | Delete blog post |

### FAQs

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| `GET` | `/api/content/faq` | No | Get all FAQs |
| `GET` | `/api/content/faq/{category}` | No | Get FAQs by category |
| `POST` | `/api/content/faq` | Admin | Create new FAQ |
| `PUT` | `/api/content/faq/{id}` | Admin | Update FAQ |
| `DELETE` | `/api/content/faq/{id}` | Admin | Delete FAQ |

### Testimonials

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| `GET` | `/api/content/testimonials` | No | Get all approved testimonials |
| `POST` | `/api/content/testimonials` | Yes | Submit new testimonial |
| `PUT` | `/api/content/testimonials/{id}/approve` | Admin | Approve testimonial |
| `DELETE` | `/api/content/testimonials/{id}` | Admin | Delete testimonial |

## 📊 Data Models

### BlogPost

```java
{
  "id": "string",
  "title": "string",
  "slug": "string",
  "content": "string",
  "excerpt": "string",
  "author": "string",
  "imageUrl": "string",
  "category": "string",
  "tags": ["string"],
  "featured": boolean,
  "published": boolean,
  "createdAt": "datetime",
  "updatedAt": "datetime"
}
```

### FAQ

```java
{
  "id": "string",
  "question": "string",
  "answer": "string",
  "category": "string",
  "order": number,
  "createdAt": "datetime"
}
```

### Testimonial

```java
{
  "id": "string",
  "userId": "string",
  "userName": "string",
  "rating": number,
  "content": "string",
  "productId": "string",
  "approved": boolean,
  "createdAt": "datetime"
}
```

## 🔧 Configuration

### Application Properties

```yaml
server:
  port: 8086

spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/CSO2_content_service
      database: CSO2_content_service

logging:
  level:
    com.cs02.content: DEBUG
```

### Environment Variables

| Variable | Required | Default | Description |
|----------|----------|---------|-------------|
| `SPRING_DATA_MONGODB_URI` | No | `mongodb://localhost:27017` | MongoDB connection URI |
| `SPRING_DATA_MONGODB_DATABASE` | No | `CSO2_content_service` | Database name |
| `SERVER_PORT` | No | `8086` | Service port |

## 📦 Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>
</dependencies>
```

## 🏃 Running the Service

### Local Development

```bash
cd backend/content-service

# Using Maven Wrapper
./mvnw spring-boot:run

# Or with Maven
mvn spring-boot:run
```

### Docker

```bash
cd backend/content-service

# Build JAR
./mvnw clean package -DskipTests

# Build Docker image
docker build -t cs02/content-service .

# Run container
docker run -p 8086:8086 \
  -e SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/CSO2_content_service \
  cs02/content-service
```

## 🗄️ Database Requirements

- **MongoDB** running on port `27017`
- Database: `CSO2_content_service`
- Collections are auto-created by Spring Data MongoDB

## ✅ Features - Completion Status

| Feature | Status | Notes |
|---------|--------|-------|
| Blog post CRUD | ✅ Complete | Full create, read, update, delete |
| Blog post slugs | ✅ Complete | SEO-friendly URLs |
| Featured posts | ✅ Complete | Flag for homepage display |
| Blog categories | ✅ Complete | Organizational categorization |
| FAQ management | ✅ Complete | Question/answer pairs |
| FAQ categories | ✅ Complete | Grouped by topic |
| Testimonials CRUD | ✅ Complete | Customer reviews |
| Testimonial approval | ✅ Complete | Admin moderation |
| MongoDB integration | ✅ Complete | Document storage |

### **Overall Completion: 85%** ✅

## ❌ Not Implemented / Future Enhancements

| Feature | Priority | Notes |
|---------|----------|-------|
| Rich text editor support | Medium | Currently plain text |
| Image upload/storage | Medium | Currently URL references only |
| Blog post scheduling | Low | Publish at future date |
| Content versioning | Low | Track content history |
| SEO meta tags | Low | Meta descriptions, keywords |
| Blog comments | Low | User comments on posts |
| Content search | Medium | Full-text search |
| Admin dashboard endpoints | Medium | Bulk operations |

## 📁 Project Structure

```
content-service/
├── src/
│   ├── main/
│   │   ├── java/com/cs02/content/
│   │   │   ├── ContentServiceApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── BlogController.java
│   │   │   │   ├── FAQController.java
│   │   │   │   └── TestimonialController.java
│   │   │   ├── model/
│   │   │   │   ├── BlogPost.java
│   │   │   │   ├── FAQ.java
│   │   │   │   └── Testimonial.java
│   │   │   ├── repository/
│   │   │   │   ├── BlogRepository.java
│   │   │   │   ├── FAQRepository.java
│   │   │   │   └── TestimonialRepository.java
│   │   │   └── service/
│   │   │       ├── BlogService.java
│   │   │       ├── FAQService.java
│   │   │       └── TestimonialService.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
├── pom.xml
├── Dockerfile
└── README.md
```

## 🧪 Testing

```bash
# Run unit tests
./mvnw test

# Test endpoints
curl http://localhost:8086/api/content/blog
curl http://localhost:8086/api/content/faq
curl http://localhost:8086/api/content/testimonials
```

## 🔗 Related Services

- [API Gateway](../api-gateway/README.md) - Routes `/api/content/*` to this service
- [Frontend](../../frontend/README.md) - Displays blog, FAQ, and testimonial content

## 📝 Notes

- Service runs on port **8086**
- Uses **MongoDB** for flexible document storage
- Blog slugs must be unique
- Testimonials require approval before public display
- All content is publicly readable, admin rights needed for modifications
