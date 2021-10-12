CREATE TABLE accounts
(
    id               bigint(20) NOT NULL AUTO_INCREMENT,
    about_me         text,
    account_id       int(11)      DEFAULT NULL,
    display_name     varchar(255) DEFAULT NULL,
    down_votes       int(11)      DEFAULT NULL,
    last_access_date datetime     DEFAULT NULL,
    location         varchar(255) DEFAULT NULL,
    up_votes         int(11)      DEFAULT NULL,
    views            int(11)      DEFAULT NULL,
    website_url      varchar(255) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY UKAccountAccountId (account_id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

CREATE TABLE tags
(
    id                bigint(20) NOT NULL AUTO_INCREMENT,
    count             int(11)      DEFAULT NULL,
    excerpt_post_id   bigint(20)   DEFAULT NULL,
    is_moderator_only bit(1)       DEFAULT NULL,
    is_required       bit(1)       DEFAULT NULL,
    tag_name          varchar(255) DEFAULT NULL,
    wiki_post_id      bigint(20)   DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY UKTagTagName (tag_name)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;
