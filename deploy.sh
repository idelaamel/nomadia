#!/bin/bash

# Deployment script for Railway/Render/Heroku

echo "🚀 Starting deployment process..."

# Build the application
echo "📦 Building the application..."
./mvnw clean package -DskipTests

# Check if build was successful
if [ $? -eq 0 ]; then
    echo "✅ Build successful!"
else
    echo "❌ Build failed!"
    exit 1
fi

echo "🎉 Application ready for deployment!"
echo ""
echo "📋 Next steps:"
echo "1. Push your code to GitHub"
echo "2. Connect your repository to Railway/Render"
echo "3. Add environment variables:"
echo "   - SPRING_PROFILES_ACTIVE=prod"
echo "   - DATABASE_URL (automatically provided by Railway)"
echo "   - GOOGLE_REDIRECT_URI=https://your-app-domain.com/login/oauth2/code/google"
echo ""
echo "🔗 Useful links:"
echo "   Railway: https://railway.app"
echo "   Render: https://render.com"
echo "   Heroku: https://heroku.com"
