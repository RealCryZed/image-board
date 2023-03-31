DROP TABLE IF EXISTS public.comment;
DROP TABLE IF EXISTS public.post;


CREATE TABLE public.post
(
    id          BIGINT PRIMARY KEY                  NOT NULL,
    article     VARCHAR(100)                        NOT NULL,
    content     TEXT                                NOT NULL,
    author_name VARCHAR(255)                        NOT NULL,
--     image       BYTEA                            ,
    created_at  TIMESTAMP DEFAULT current_timestamp NOT NULL
);

CREATE TABLE public.comment
(
    id         BIGINT PRIMARY KEY            NOT NULL,
    author     VARCHAR(255)                        NOT NULL,
    content    TEXT                                NOT NULL,
-- #     image BYTEA NOT NULL,
    post_id    BIGINT                        NOT NULL,
    created_at TIMESTAMP DEFAULT current_timestamp NOT NULL,
    CONSTRAINT fk_comments_posts FOREIGN KEY (post_id) REFERENCES post (id)
)