INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO users (username, email, password, role_id)
VALUES ('Admin', 'rapira_by@mail.ru', '$2a$10$yLSUP3gdpPO0Dnu0woAB/Ofx1GGRwHEw7Y2R6.OtNACPIsaQSsQ7i', 1),
       ('User', 'test@mail.ru', '$2a$10$io4nSdCqWEnYDAawrQXuFeqFurC1pAOC2Ea0NEWUupzEuAZHOeJUW', 2);

INSERT INTO tasks (name, author_id, task_status, task_priority, deadline)
       VALUES ('Task1', 1, 'IN_PROGRESS', 'HIGH', '2024-11-30T08:00:00.0000');

INSERT INTO comments (message, date_added, task_id, author_id)
       VALUES ('Type something', '2024-11-24T19:07:23.973Z', 1, 1);
