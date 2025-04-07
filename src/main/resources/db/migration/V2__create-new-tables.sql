CREATE TABLE project (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    available_time INT NOT NULL,
    purchasing_requirements TEXT NULL,
    nda_required BOOLEAN NOT NULL DEFAULT FALSE,
    showcase_allowed BOOLEAN NOT NULL DEFAULT TRUE,
    semester ENUM(
        'FALL_2025', 'WINTER_2025', 'SPRING_2025',
        'FALL_2026', 'WINTER_2026', 'SPRING_2026',
        'FALL_2027', 'WINTER_2027', 'SPRING_2027',
        'FALL_2028', 'WINTER_2028', 'SPRING_2028',
        'FALL_2029', 'WINTER_2029', 'SPRING_2029',
        'FALL_2030', 'WINTER_2030', 'SPRING_2030'
    ) NOT NULL,
    organization_id INT NOT NULL,
    professor_id INT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_project_organization FOREIGN KEY (organization_id) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT fk_project_professor FOREIGN KEY (professor_id) REFERENCES user(id) ON DELETE SET NULL
);

CREATE INDEX idx_project_organization ON project(organization_id);
CREATE INDEX idx_project_professor ON project(professor_id);

CREATE TABLE project_validation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    professor_id INT NOT NULL,
    professor_feedback TEXT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_validation_project FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE,
    CONSTRAINT fk_validation_professor FOREIGN KEY (professor_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE tag_type (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE tag_value (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tag_type_id INT NOT NULL,
    value VARCHAR(100) NOT NULL,

    CONSTRAINT fk_tag_value_type FOREIGN KEY (tag_type_id) REFERENCES tag_type(id) ON DELETE CASCADE
);

CREATE TABLE project_tag (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    tag_value_id INT NOT NULL,
    professor_id INT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_project_tag_project FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE,
    CONSTRAINT fk_project_tag_value FOREIGN KEY (tag_value_id) REFERENCES tag_value(id) ON DELETE CASCADE,
    CONSTRAINT fk_project_tag_professor FOREIGN KEY (professor_id) REFERENCES user(id) ON DELETE SET NULL
);