CREATE TABLE IF NOT EXISTS roles
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
    id          SERIAL PRIMARY KEY,
    username    VARCHAR(64) NOT NULL UNIQUE,
    email       VARCHAR(64) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role_id     SERIAL references roles (id) ON DELETE CASCADE
);

DROP TYPE IF EXISTS task_status_enum;

CREATE TYPE task_status_enum AS ENUM ('PENDING', 'IN_PROGRESS', 'COMPLETED', 'CANCELED');

DROP TYPE IF EXISTS task_priority_enum;

CREATE TYPE task_priority_enum AS ENUM ('LOW', 'MEDIUM', 'HIGH', 'CRITICAL');

CREATE TABLE IF NOT EXISTS tasks
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(64) NOT NULL,
    author_id     SERIAL REFERENCES users (id),
    task_status   task_status_enum,
    task_priority task_priority_enum,
    deadline      TIMESTAMP
);

CREATE TABLE IF NOT EXISTS comments
(
    id          SERIAL PRIMARY KEY,
    message     VARCHAR(512) NOT NULL,
    date_added  TIMESTAMP,
    task_id     SERIAL REFERENCES tasks (id),
    author_id   SERIAL REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS employee_task
(
    employee_id INTEGER NOT NULL,
    task_id INTEGER NOT NULL,
	PRIMARY KEY (employee_id, task_id),
	FOREIGN KEY (employee_id) REFERENCES users(id),
	FOREIGN KEY (task_id) REFERENCES tasks(id)
);
