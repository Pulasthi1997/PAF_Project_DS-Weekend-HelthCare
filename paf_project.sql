-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2020 at 04:30 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paf_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(50) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `Username`, `Password`, `Type`) VALUES
(1, 'Pulasthi', '123', '1'),
(2, 'Sheran', '123', '2');

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `appointmentID` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `patientID` int(11) NOT NULL,
  `doctorID` int(11) NOT NULL,
  `paymentID` int(11) NOT NULL,
  `appointmentStatus` varchar(10) NOT NULL DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `Doctor_ID` int(11) NOT NULL,
  `D_Name` varchar(100) NOT NULL,
  `D_Type` varchar(100) NOT NULL,
  `D_Contact_Number` int(11) NOT NULL,
  `D_Address` varchar(100) NOT NULL,
  `D_Email` varchar(50) NOT NULL,
  `Hospital_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`Doctor_ID`, `D_Name`, `D_Type`, `D_Contact_Number`, `D_Address`, `D_Email`, `Hospital_ID`) VALUES
(11, 'Kavishka Sheran', 'Rheumatologist', 779018851, 'No66,HospitalRD,Aluthgama', 'IT12@gmail.com', 1),
(12, 'Samith Karunathilake', 'Rheumatologist', 769045277, 'Moratuwa', 'Praveen@gmail.com', 3),
(13, 'Samith Uddika', 'Pediatrician', 772701828, 'No55,GalleRD,Malabe', 'IT55@gmail.com', 3),
(14, 'Praveen Fernando', 'Dentist', 342270520, 'No33,GalleRD,Moratuva', 'IT567@gmail.com', 3);

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `H_ID` int(10) NOT NULL,
  `H_name` varchar(50) NOT NULL,
  `H_contactNumber` int(10) NOT NULL,
  `H_address` varchar(50) NOT NULL,
  `H_email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`H_ID`, `H_name`, `H_contactNumber`, `H_address`, `H_email`) VALUES
(1, 'National Eye Hospital of Sri Lanka', 112693911, ' Rev. Baddegama Wimalawansa Thero Mawatha, Ven Bad', 'http://www.nationaleyehospital.health.gov.lk/'),
(2, 'National Hospital of Sri Lanka', 112691111, 'Colombo 00700', 'http://www.nhsl.health.gov.lk/'),
(3, 'Durdense Hospitals', 112140000, '3 Alfred Pl, Colombo 00300', 'https://www.durdans.lk/'),
(16, 'Lanka Hospitals', 110203620, 'Colombo', 'lh@gmail.com'),
(19, 'Lanka Hospital', 777485123, 'colombo', 'lankahospital@gmail.com'),
(20, 'Lady Ridge Way', 112784595, 'Narahenpita', 'ladyridgeway@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payID` int(50) NOT NULL,
  `paytype` varchar(50) NOT NULL,
  `cno` varchar(50) NOT NULL,
  `expdate` varchar(10) NOT NULL,
  `code` varchar(10) NOT NULL,
  `AppointmentId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `User_ID` int(11) NOT NULL,
  `User_Name` varchar(100) NOT NULL,
  `U_NIC` varchar(100) NOT NULL,
  `U_Age` int(11) NOT NULL,
  `U_Contact_Number` int(11) NOT NULL,
  `U_Email` varchar(100) NOT NULL,
  `U_Address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`appointmentID`),
  ADD KEY `patientID` (`patientID`),
  ADD KEY `fk_doctor` (`doctorID`),
  ADD KEY `fk_payment` (`paymentID`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`Doctor_ID`),
  ADD KEY `Hospital_ID` (`Hospital_ID`);

--
-- Indexes for table `hospital`
--
ALTER TABLE `hospital`
  ADD PRIMARY KEY (`H_ID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payID`),
  ADD KEY `AppointmentId` (`AppointmentId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`User_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `appointmentID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `Doctor_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `hospital`
--
ALTER TABLE `hospital`
  MODIFY `H_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payID` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `User_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `fk_doctor` FOREIGN KEY (`doctorID`) REFERENCES `doctor` (`Doctor_ID`),
  ADD CONSTRAINT `fk_patient` FOREIGN KEY (`patientID`) REFERENCES `user` (`User_ID`),
  ADD CONSTRAINT `fk_payment` FOREIGN KEY (`paymentID`) REFERENCES `payment` (`payID`);

--
-- Constraints for table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`Hospital_ID`) REFERENCES `hospital` (`H_ID`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`AppointmentId`) REFERENCES `appointment` (`appointmentID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
