# Such.cab App

A modern ClojureScript web application with user authentication, built using re-frame, React, and Material-UI.

## Features

- User authentication (login and signup)
- Form validation (email format, password requirements)
- Error and success message handling
- Loading states
- Authenticated dashboard view
- Session management
- Material Design UI
- Hot reloading during development

## Technology Stack

### Frontend
- **ClojureScript** - Functional programming for the browser
- **React** - UI library
- **re-frame** - State management (flux-like architecture)
- **Reagent** - React wrapper for ClojureScript
- **Material-UI** - Material Design components
- **Garden** - CSS-in-Clojure

### Backend
- **Clojure** - Backend server
- **Ring** - HTTP server abstraction
- **Compojure** - Routing library
- **Buddy** - Password hashing

### Build Tools
- **shadow-cljs** - ClojureScript compiler with hot reloading
- **Leiningen** - Project automation

## Prerequisites

- Java 8 or higher
- Leiningen 2.5.3 or higher
- Node.js and npm

## Installation

1. Install dependencies:
```bash
lein deps
npm install
```

2. Compile CSS:
```bash
lein garden auto
```

## Running the Application

### Start the Backend Server

In one terminal, start the backend API server:

```bash
lein server
```

The server will run on `http://localhost:3000`

### Start the Frontend Development Server

In another terminal, start the frontend development server:

```bash
lein dev
```

The application will be available at `http://localhost:8280`

### Access the Application

Open your browser and navigate to:
```
http://localhost:8280
```

## Usage

### Creating an Account

1. Enter a valid email address
2. Enter a password (minimum 6 characters)
3. Click "Sign Up"
4. You'll be redirected to the dashboard upon success

### Logging In

1. Enter your email and password
2. Click "Log In"
3. You'll be redirected to the dashboard upon success

### Validation Rules

- **Email**: Must be a valid email format (e.g., user@example.com)
- **Password**: Must be at least 6 characters long

## Development

### Hot Reloading

The development server includes hot reloading. Changes to your ClojureScript code will be automatically recompiled and reflected in the browser.

### CSS Development

To automatically recompile CSS on changes:

```bash
lein garden auto
```

### REPL

Connect to the nREPL server on port 8777 for interactive development.

## Testing

Run the test suite:

```bash
lein karma
```

## Production Build

Create a production build:

```bash
lein prod
```

The compiled files will be in `resources/public/js/compiled/`

## Project Structure

```
suchcab-app/
├── src/
│   ├── clj/                    # Clojure (backend) code
│   │   └── suchcab_app/
│   │       ├── server.clj      # Backend API server
│   │       └── css.clj         # Garden CSS definitions
│   └── cljs/                   # ClojureScript (frontend) code
│       └── suchcab_app/
│           ├── core.cljs       # Application entry point
│           ├── views.cljs      # React components
│           ├── components.cljs # Material-UI wrappers
│           ├── events.cljs     # re-frame event handlers
│           ├── subs.cljs       # re-frame subscriptions
│           ├── db.cljs         # Database schema
│           └── config.cljs     # Configuration
├── test/                       # Test files
│   └── cljs/
│       └── suchcab_app/
│           └── events_test.cljs
├── resources/
│   └── public/
│       ├── index.html          # HTML entry point
│       ├── js/compiled/        # Compiled ClojureScript
│       └── css/                # Generated CSS
├── project.clj                 # Leiningen configuration
├── shadow-cljs.edn             # shadow-cljs configuration
├── package.json                # npm dependencies
└── karma.conf.js               # Test runner configuration
```

## API Endpoints

### POST /api/user/create
Create a new user account

**Request:**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response (Success):**
```json
{
  "success": true,
  "user": {
    "email": "user@example.com",
    "created-at": "..."
  },
  "message": "User created successfully"
}
```

### POST /api/user/login
Authenticate a user

**Request:**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response (Success):**
```json
{
  "success": true,
  "user": {
    "email": "user@example.com",
    "created-at": "..."
  },
  "message": "Login successful"
}
```

### GET /api/health
Health check endpoint

**Response:**
```json
{
  "status": "ok"
}
```

## State Management

The application uses re-frame for state management. The application state includes:

- `current-view` - Current view (:login or :dashboard)
- `user` - Logged-in user information
- `loading` - Loading state for async operations
- `error` - Error messages
- `success` - Success messages
- `form-errors` - Form validation errors

## Security Notes

**Development Mode Only:**
- The current implementation stores users in memory (will be lost on server restart)
- For production use, implement proper database storage
- Add HTTPS/TLS encryption
- Implement proper session management with tokens
- Add rate limiting for authentication endpoints
- Consider adding CSRF protection

## License

Copyright © 2025
