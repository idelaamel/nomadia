@echo off
echo 🚀 Starting deployment process...

echo 📦 Building the application...
call mvnw.cmd clean package -DskipTests

if %ERRORLEVEL% EQU 0 (
    echo ✅ Build successful!
) else (
    echo ❌ Build failed!
    exit /b 1
)

echo 🎉 Application ready for deployment!
echo.
echo 📋 Next steps:
echo 1. Push your code to GitHub
echo 2. Connect your repository to Railway/Render
echo 3. Add environment variables:
echo    - SPRING_PROFILES_ACTIVE=prod
echo    - DATABASE_URL (automatically provided by Railway)
echo    - GOOGLE_REDIRECT_URI=https://your-app-domain.com/login/oauth2/code/google
echo.
echo 🔗 Useful links:
echo    Railway: https://railway.app
echo    Render: https://render.com
echo    Heroku: https://heroku.com
