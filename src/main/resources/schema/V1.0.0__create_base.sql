CREATE TABLE base.payment
(
    id       VARCHAR(40) NOT NULL,
    tenantId VARCHAR(40) NOT NULL,
    value    VARCHAR(40) NOT NULL,
    PRIMARY KEY (id, tenantId)
);
INSERT INTO base.payment(id, tenantId, value)
VALUES ('1', 'company1', '100$');
INSERT INTO base.payment(id, tenantId, value)
VALUES ('1', 'company2', '200$');
INSERT INTO base.payment(id, tenantId, value)
VALUES ('2', 'company2', '400$');