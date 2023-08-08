CREATE TABLE primary_entity
(
    "ID"                  bigint NOT NULL,
    "SECONDARY_ENTITY_ID" bigint,
    "CATEGORY"            VARCHAR(255)
);

CREATE TABLE secondary_entity
(
    "ID"              bigint NOT NULL,
    "ADDITIONAL_DATA" VARCHAR(255)
);


