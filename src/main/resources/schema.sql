CREATE TABLE IF NOT EXISTS PRODUCT (id IDENTITY NOT NULL PRIMARY KEY, parameter varchar, name VARCHAR(100), stock INTEGER);

CREATE TABLE IF NOT EXISTS OTR_APPLICATION_PARAMETER (id IDENTITY NOT NULL PRIMARY KEY, parameter VARCHAR(256), value VARCHAR(256));

DROP TABLE IF EXISTS pakketten;
CREATE TABLE IF NOT EXISTS pakketten (id IDENTITY NOT NULL PRIMARY KEY, totaal long, bbpx long, agt long, totaal_postbusbezorgd_dt TIMESTAMP, bbpx_postbusbezorgd_dt TIMESTAMP, agt_postbusbezorgd_dt TIMESTAMP, ingangsdt VARCHAR, eta_van TIMESTAMP, postcd VARCHAR, huisnr VARCHAR, huisnrtvg VARCHAR, producttype VARCHAR);
