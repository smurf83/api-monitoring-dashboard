-- Sample data for testing the Task Management API

INSERT INTO tasks (title, description, priority, status, due_date, created_at, updated_at) 
VALUES 
('Complete project documentation', 'Write comprehensive documentation for the new API endpoints', 'HIGH', 'IN_PROGRESS', '2024-01-15 10:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Code review for feature branch', 'Review pull request for new authentication feature', 'MEDIUM', 'TODO', '2024-01-12 14:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fix critical bug in payment system', 'Address the payment processing issue reported by customer', 'URGENT', 'TODO', '2024-01-10 09:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Update user interface', 'Improve the UI/UX based on user feedback', 'LOW', 'TODO', '2024-01-20 16:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Database migration', 'Migrate user data to new database schema', 'HIGH', 'COMPLETED', '2024-01-08 11:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Setup CI/CD pipeline', 'Configure automated testing and deployment pipeline', 'MEDIUM', 'IN_PROGRESS', '2024-01-18 13:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Team meeting preparation', 'Prepare agenda and materials for weekly team meeting', 'LOW', 'COMPLETED', '2024-01-09 15:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Security audit', 'Conduct security assessment of the application', 'HIGH', 'TODO', '2024-01-25 10:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);