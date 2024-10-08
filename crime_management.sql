CREATE DATABASE IF NOT EXISTS Crime_Management;
use Crime_Management;

-- Law Enforcement Agencies Table
CREATE TABLE LawEnforcementAgencies (
    AgencyID INT PRIMARY KEY ,
    AgencyName VARCHAR(255) NOT NULL,
    Jurisdiction VARCHAR(255),
    ContactInformation TEXT
);

-- Officers Table
CREATE TABLE Officers (
    OfficerID INT PRIMARY KEY ,
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    BadgeNumber VARCHAR(50) NOT NULL,
    OfficerRank VARCHAR(100), -- Renamed the column
    ContactInformation TEXT,
    AgencyID INT,
    FOREIGN KEY (AgencyID) REFERENCES LawEnforcementAgencies(AgencyID)
);

-- Victims Table
CREATE TABLE Victims (
    VictimID INT PRIMARY KEY ,
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    DateOfBirth DATE,
    Gender VARCHAR(10),
    ContactInformation TEXT
);

-- Suspects Table
CREATE TABLE Suspects (
    SuspectID INT PRIMARY KEY ,
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    DateOfBirth DATE,
    Gender VARCHAR(10),
    ContactInformation TEXT
);

CREATE TABLE Incidents (
    IncidentID INT PRIMARY KEY ,
    IncidentType VARCHAR(255) NOT NULL,
    IncidentDate DATE,
    Location VARCHAR(50),-- Geospatial information
    Description TEXT,
    Status VARCHAR(50),
    VictimID INT,
    SuspectID INT,
    FOREIGN KEY (VictimID) REFERENCES Victims(VictimID),
    FOREIGN KEY (SuspectID) REFERENCES Suspects(SuspectID)
);

-- Cases
CREATE TABLE Cases (
    CaseID INT PRIMARY KEY ,
    CaseDescription TEXT,
    IncidentID INT,
    FOREIGN KEY (IncidentID) REFERENCES Incidents(IncidentID)
);

-- Evidence Table
CREATE TABLE Evidence (
    EvidenceID INT PRIMARY KEY ,
    Description TEXT NOT NULL,
    LocationFound VARCHAR(255),
    IncidentID INT,
    FOREIGN KEY (IncidentID) REFERENCES Incidents(IncidentID)
);

-- Reports Table
CREATE TABLE Reports (
    ReportID INT PRIMARY KEY ,
    IncidentID INT,
    ReportingOfficer INT,
    ReportDate DATE,
    ReportDetails TEXT,
    Status VARCHAR(50),
    FOREIGN KEY (IncidentID) REFERENCES Incidents(IncidentID),
    FOREIGN KEY (ReportingOfficer) REFERENCES Officers(OfficerID)
);


-- Inserting sample data without auto-increment
INSERT INTO LawEnforcementAgencies (AgencyID, AgencyName, Jurisdiction, ContactInformation)
VALUES
(101, 'Mumbai Police Department', 'Mumbai', 'Contact: 022-22621855, Address: Mumbai Police HQ, Crawford Market, Mumbai'),
(102, 'Delhi Police', 'Delhi', 'Contact: 011-23490000, Address: Delhi Police HQ, Jai Singh Marg, Connaught Place, New Delhi'),
(103, 'Bengaluru Police', 'Bengaluru', 'Contact: 080-22942222, Address: Bengaluru Police HQ, Infantry Road, Bengaluru'),
(104, 'Kolkata Police', 'Kolkata', 'Contact: 033-22143024, Address: Lalbazar Street, Kolkata'),
(105, 'Chennai Police', 'Chennai', 'Contact: 044-23452345, Address: Vepery, Chennai');

INSERT INTO Officers (OfficerID, FirstName, LastName, BadgeNumber, OfficerRank, ContactInformation, AgencyID)
VALUES
(201, 'Rajesh', 'Sharma', 'MPD001', 'Inspector', 'Phone: 022-22621855, Email: rajesh.sharma@mumbaipolice.gov.in', 101),
(202, 'Anita', 'Rao', 'DP002', 'Sub-Inspector', 'Phone: 011-23490001, Email: anita.rao@delhipolice.gov.in', 102),
(203, 'Suresh', 'Patil', 'MPD002', 'Head Constable', 'Phone: 022-22621856, Email: suresh.patil@mumbaipolice.gov.in', 101),
(204, 'Vikram', 'Kumar', 'BLP003', 'Deputy Inspector', 'Phone: 080-22942223, Email: vikram.kumar@bengalurupolice.gov.in', 103),
(205, 'Manisha', 'Iyer', 'CP004', 'Inspector', 'Phone: 044-23452346, Email: manisha.iyer@chennaipolice.gov.in', 105);

INSERT INTO Victims (VictimID, FirstName, LastName, DateOfBirth, Gender, ContactInformation)
VALUES
(301, 'Amit', 'Kumar', '1985-05-14', 'Male', 'Address: 24 B, Marine Drive, Mumbai, Phone: 9820345678'),
(302, 'Priya', 'Singh', '1990-07-21', 'Female', 'Address: 45 A, Karol Bagh, New Delhi, Phone: 9876543210'),
(303, 'Ramesh', 'Babu', '1978-10-09', 'Male', 'Address: 14 D, Jayanagar, Bengaluru, Phone: 9887765543'),
(304, 'Sunita', 'Verma', '1995-12-15', 'Female', 'Address: 35 C, Salt Lake, Kolkata, Phone: 9874345654'),
(305, 'Neha', 'Deshmukh', '1987-04-18', 'Female', 'Address: 42 E, Egmore, Chennai, Phone: 9867854321');

INSERT INTO Suspects (SuspectID, FirstName, LastName, DateOfBirth, Gender, ContactInformation)
VALUES
(401, 'Ravi', 'Verma', '1980-11-25', 'Male', 'Address: 12 C, Dharavi, Mumbai, Phone: 9834567890'),
(402, 'Meera', 'Gupta', '1992-03-19', 'Female', 'Address: 34 D, Old Delhi, Phone: 9898765432'),
(403, 'Kiran', 'Shetty', '1983-07-05', 'Male', 'Address: 54 F, Koramangala, Bengaluru, Phone: 9900654321'),
(404, 'Rahul', 'Roy', '1979-01-22', 'Male', 'Address: 27 A, Howrah, Kolkata, Phone: 9874453210'),
(405, 'Shivani', 'Desai', '1990-06-12', 'Female', 'Address: 29 B, Mylapore, Chennai, Phone: 9845551234');

INSERT INTO Incidents (IncidentID, IncidentType, IncidentDate, Location, Description, Status, VictimID, SuspectID)
VALUES
(501, 'Robbery', '2024-01-15', 'Colaba, Mumbai', 'A daylight robbery at a jewelry store', 'Open', 301, 401),
(502, 'Fraud', '2024-03-25', 'Connaught Place, New Delhi', 'Financial fraud at a local bank', 'Under Investigation', 302, 402),
(503, 'Assault', '2024-05-05', 'Jayanagar, Bengaluru', 'Assault in a residential area', 'Closed', 303, 403),
(504, 'Burglary', '2024-07-12', 'Salt Lake, Kolkata', 'Home burglary in Salt Lake', 'Open', 304, 404),
(505, 'Kidnapping', '2024-09-20', 'Egmore, Chennai', 'Kidnapping reported in the Egmore area', 'Ongoing', 305, 405);

INSERT INTO Cases (CaseID, CaseDescription, IncidentID)
VALUES
(601, 'Jewelry store robbery investigation in Colaba', 501),
(602, 'Bank fraud investigation in New Delhi', 502),
(603, 'Assault case in Jayanagar', 503),
(604, 'Home burglary in Salt Lake', 504),
(605, 'Kidnapping case in Egmore', 505);

INSERT INTO Evidence (EvidenceID, Description, LocationFound, IncidentID)
VALUES
(701, 'CCTV footage of the robbery', 'Colaba Jewelry Store, Mumbai', 501),
(702, 'Bank transaction records', 'New Delhi Bank, Connaught Place', 502),
(703, 'Witness statement', 'Jayanagar, Bengaluru', 503),
(704, 'Fingerprint evidence', 'Salt Lake Residence, Kolkata', 504),
(705, 'Vehicle footage', 'Egmore, Chennai', 505);

INSERT INTO Reports (ReportID, IncidentID, ReportingOfficer, ReportDate, ReportDetails, Status)
VALUES
(801, 501, 201, '2024-01-16', 'Initial investigation report for the jewelry store robbery', 'Open'),
(802, 502, 202, '2024-03-26', 'Preliminary report on the bank fraud investigation', 'Under Investigation'),
(803, 503, 203, '2024-05-06', 'Report on the assault case in Jayanagar', 'Closed'),
(804, 504, 204, '2024-07-13', 'Investigation report for Salt Lake burglary', 'Open'),
(805, 505, 205, '2024-09-21', 'Initial report on the kidnapping case in Egmore', 'Ongoing');