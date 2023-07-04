CREATE TABLE IF NOT EXISTS film
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    title
    VARCHAR
(
    255
) NOT NULL,
    description VARCHAR
(
    255
) NOT NULL,
    release_year VARCHAR
(
    255
) NOT NULL,
    duration VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS category
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    category_name
    VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS film_category
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    film
    BIGINT
    NOT
    NULL,
    category
    BIGINT
    NOT
    NULL,

    PRIMARY
    KEY
(
    id
),
    FOREIGN KEY
(
    film
) REFERENCES film
(
    id
),
    FOREIGN KEY
(
    category
) REFERENCES category
(
    id
)
    );

CREATE TABLE IF NOT EXISTS country
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    country_name
    VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS city
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    city_name
    VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS address
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    address
    VARCHAR
(
    255
) NOT NULL,
    postal_code VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS cinema
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    cinema_name
    VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS room
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    room_number
    BIGINT
    NOT
    NULL,
    capacity
    BIGINT
    NOT
    NULL,

    PRIMARY
    KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS languages
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    language_name
    VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS film_languages
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    film
    BIGINT
    NOT
    NULL,
    languages
    BIGINT
    NOT
    NULL,

    PRIMARY
    KEY
(
    id
),
    FOREIGN KEY
(
    film
) REFERENCES film
(
    id
),
    FOREIGN KEY
(
    languages
) REFERENCES languages
(
    id
)
    );

CREATE TABLE IF NOT EXISTS actor
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    first_name
    VARCHAR
(
    255
) NOT NULL,
    last_name VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );


CREATE TABLE IF NOT EXISTS film_actor
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    film
    BIGINT
    NOT
    NULL,
    actor
    BIGINT
    NOT
    NULL,

    PRIMARY
    KEY
(
    id
),
    FOREIGN KEY
(
    film
) REFERENCES film
(
    id
),
    FOREIGN KEY
(
    actor
) REFERENCES actor
(
    id
)
    );

CREATE TABLE IF NOT EXISTS film
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    title
    VARCHAR
(
    255
) NOT NULL,
    description VARCHAR
(
    255
) NOT NULL,
    release_year VARCHAR
(
    255
) NOT NULL,
    duration VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS screening
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    start_time
    VARCHAR
(
    255
) NOT NULL,
    end_time VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS customer
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    customer_first
    VARCHAR
(
    255
) NOT NULL,
    customer_last VARCHAR
(
    255
) NOT NULL,
    email VARCHAR
(
    255
) NOT NULL,
    phone VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS ticket
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    price
    VARCHAR
(
    255
) NOT NULL,
    seat_number VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS screening_film
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    screening
    BIGINT
    NOT
    NULL,
    film
    BIGINT
    NOT
    NULL,

    PRIMARY
    KEY
(
    id
),
    FOREIGN KEY
(
    screening
) REFERENCES screening
(
    id
),
    FOREIGN KEY
(
    film
) REFERENCES film
(
    id
)
    );

CREATE TABLE IF NOT EXISTS ticket_screening
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    ticket
    BIGINT
    NOT
    NULL,
    screening
    BIGINT
    NOT
    NULL,

    PRIMARY
    KEY
(
    id
),
    FOREIGN KEY
(
    ticket
) REFERENCES ticket
(
    id
),
    FOREIGN KEY
(
    screening
) REFERENCES screening
(
    id
)
    );

CREATE TABLE IF NOT EXISTS ticket_customer
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    ticket
    BIGINT
    NOT
    NULL,
    customer
    BIGINT
    NOT
    NULL,

    PRIMARY
    KEY
(
    id
),
    FOREIGN KEY
(
    ticket
) REFERENCES ticket
(
    id
),
    FOREIGN KEY
(
    customer
) REFERENCES customer
(
    id
)
    );

CREATE TABLE IF NOT EXISTS city_country
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    city
    BIGINT
    NOT
    NULL,
    country
    BIGINT
    NOT
    NULL,

    PRIMARY
    KEY
(
    id
),
    FOREIGN KEY
(
    city
) REFERENCES city
(
    id
),
    FOREIGN KEY
(
    country
) REFERENCES country
(
    id
)
    );

CREATE TABLE IF NOT EXISTS address_city
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    address
    BIGINT
    NOT
    NULL,
    city
    BIGINT
    NOT
    NULL,

    PRIMARY
    KEY
(
    id
),
    FOREIGN KEY
(
    address
) REFERENCES address
(
    id
),
    FOREIGN KEY
(
    city
) REFERENCES city
(
    id
)
    );

CREATE TABLE IF NOT EXISTS cinema_address
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    cinema
    BIGINT
    NOT
    NULL,
    address
    BIGINT
    NOT
    NULL,

    PRIMARY
    KEY
(
    id
),
    FOREIGN KEY
(
    cinema
) REFERENCES cinema
(
    id
),
    FOREIGN KEY
(
    address
) REFERENCES address
(
    id
)
    );

CREATE TABLE IF NOT EXISTS room_cinema
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    room
    BIGINT
    NOT
    NULL,
    cinema
    BIGINT
    NOT
    NULL,

    PRIMARY
    KEY
(
    id
),
    FOREIGN KEY
(
    room
) REFERENCES room
(
    id
),
    FOREIGN KEY
(
    cinema
) REFERENCES cinema
(
    id
)
    );

CREATE TABLE IF NOT EXISTS screening_room
(
    id
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    screening
    BIGINT
    NOT
    NULL,
    room
    BIGINT
    NOT
    NULL,

    PRIMARY
    KEY
(
    id
),
    FOREIGN KEY
(
    screening
) REFERENCES screening
(
    id
),
    FOREIGN KEY
(
    room
) REFERENCES room
(
    id
)
    );