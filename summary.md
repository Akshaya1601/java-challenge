## Implementation Notes

The employee API is implemented using a controller-service structure to keep HTTP request handling separate from application logic.

- `EmployeeController` exposes the three required REST endpoints for retrieving and creating employees.
- `EmployeeService` contains the employee retrieval and creation logic.
- Employees are stored in an in-memory `ConcurrentHashMap` keyed by UUID. This provides a simple thread-safe store without introducing a persistence layer, which is outside the scope of this challenge.
- `EmployeeModel` implements the provided `Employee` interface.
- `CreateEmployeeRequest` is used as the request model for employee creation so that client-provided fields are kept separate from server-managed fields such as UUID and full name.
- Requests for employee UUIDs that do not exist return an HTTP 404 response.

### API Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/api/v1/employee` | Returns all employees |
| GET | `/api/v1/employee/{uuid}` | Returns an employee by UUID |
| POST | `/api/v1/employee` | Creates and returns a new employee |

### Running the Application

```bash
./gradlew :api:bootRun