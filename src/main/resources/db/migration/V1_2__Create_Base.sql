CREATE TABLE users
(
    user_id          bigint(20) NOT NULL AUTO_INCREMENT,
    about_me         text,
    display_name     varchar(255) DEFAULT NULL,
    down_votes       int(11)      DEFAULT NULL,
    last_access_date datetime     DEFAULT NULL,
    location         varchar(255) DEFAULT NULL,
    up_votes         int(11)      DEFAULT NULL,
    views            int(11)      DEFAULT NULL,
    website_url      varchar(255) DEFAULT NULL,
    created_at       datetime     DEFAULT NULL,
    delete_at        bit(1)       DEFAULT NULL,
    enabled_at       bit(1)       DEFAULT NULL,
    updated_at       datetime     DEFAULT NULL,
    PRIMARY KEY (user_id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

CREATE TABLE tags
(
    tag_id            bigint(20) NOT NULL AUTO_INCREMENT,
    count             int(11)      DEFAULT NULL,
    excerpt_post_id   bigint(20)   DEFAULT NULL,
    is_moderator_only bit(1)       DEFAULT NULL,
    is_required       bit(1)       DEFAULT NULL,
    tag_name          varchar(255) DEFAULT NULL,
    wiki_post_id      bigint(20)   DEFAULT NULL,
    created_at        datetime     DEFAULT NULL,
    delete_at         bit(1)       DEFAULT NULL,
    enabled_at        bit(1)       DEFAULT NULL,
    updated_at        datetime     DEFAULT NULL,
    PRIMARY KEY (tag_id),
    UNIQUE KEY UKTagTagName (tag_name)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

CREATE TABLE posts
(
    post_id             bigint(20) NOT NULL AUTO_INCREMENT,
    created_at          datetime     DEFAULT NULL,
    delete_at           bit(1)       DEFAULT NULL,
    enabled_at          bit(1)       DEFAULT NULL,
    updated_at          datetime     DEFAULT NULL,
    answer_count        int(11)      DEFAULT NULL,
    body                text,
    comment_count       int(11)      DEFAULT NULL,
    content_license     varchar(255) DEFAULT NULL,
    creation_date       datetime     DEFAULT NULL,
    last_activity_date  datetime     DEFAULT NULL,
    last_edit_date      datetime     DEFAULT NULL,
    post_type_id        int(11)      DEFAULT NULL,
    score               int(11)      DEFAULT NULL,
    title               varchar(255) DEFAULT NULL,
    accepted_answer_id  bigint(20)   DEFAULT NULL,
    last_editor_user_id bigint(20)   DEFAULT NULL,
    owner_user_id       bigint(20)   DEFAULT NULL,
    parent_id           bigint(20)   DEFAULT NULL,
    PRIMARY KEY (post_id),
    KEY FKPostAcceptAnswerId (accepted_answer_id),
    KEY FKPostLastEditorUserId (last_editor_user_id),
    KEY FKPostOwnerUserId (owner_user_id),
    KEY FKPostParentId (parent_id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

CREATE TABLE posts_tags
(
    post_id bigint(20) NOT NULL,
    tag_id  bigint(20) NOT NULL,
    KEY FKPostTagTagId (tag_id),
    KEY FKPostTagPostId (post_id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

CREATE TABLE comments
(
    comment_id        bigint(20) NOT NULL AUTO_INCREMENT,
    created_at        datetime     DEFAULT NULL,
    delete_at         bit(1)       DEFAULT NULL,
    enabled_at        bit(1)       DEFAULT NULL,
    updated_at        datetime     DEFAULT NULL,
    content_license   varchar(255) DEFAULT NULL,
    creation_date     datetime     DEFAULT NULL,
    score             int(11)      DEFAULT NULL,
    text              text,
    user_display_name varchar(255) DEFAULT NULL,
    post_id           bigint(20)   DEFAULT NULL,
    user_id           bigint(20)   DEFAULT NULL,
    PRIMARY KEY (comment_id),
    KEY FKCommentPostId (post_id),
    KEY FKCommentUserId (user_id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

