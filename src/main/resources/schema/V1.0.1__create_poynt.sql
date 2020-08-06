CREATE TABLE poynt.payment
(
    id       VARCHAR(40) NOT NULL,
    tenantId VARCHAR(40) NOT NULL,
    value    VARCHAR(40) NOT NULL,
    PRIMARY KEY (id, tenantId)
);
INSERT INTO poynt.payment(id, tenantId, value)
VALUES ('1', 'poynt', '10001$');
INSERT INTO poynt.payment(id, tenantId, value)
VALUES ('2', 'poynt', '200001$');
INSERT INTO poynt.payment(id, tenantId, value)
VALUES ('3', 'poynt', '400001$');