-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 26, 2022 at 03:58 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `patient_record`
--

-- --------------------------------------------------------

--
-- Table structure for table `contact_info`
--

CREATE TABLE `contact_info` (
  `id_ci` int(100) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `email` varchar(30) NOT NULL,
  `medicalNum` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `contact_info`
--

INSERT INTO `contact_info` (`id_ci`, `phone`, `email`, `medicalNum`) VALUES
(1, '+38551213325', 'test@test.com', '123456789'),
(2, '+38551489965', 'test1@test.com', '987654321'),
(3, '+38551123456', 'test2@test.com', '654897321'),
(4, '+38551654789', 'tester@test.com', '321584697'),
(5, '+38551589645', 'testing@test.com', '205614892'),
(6, '+38551489654', 'test12@test.com', '546254154'),
(7, '+38551698654', 'test58@test.com', '105485136'),
(8, '+38516986546', 'test58@test.com', '105485100'),
(9, '+38551986546', 'test57@test.com', '105875100'),
(10, '+38551986546', 'test57@test.com', '105875180'),
(11, '+38551986541', 'test77@test.com', '105875122'),
(12, '+38519865421', 'test17@test.com', '105875133'),
(13, '+38519865422', 'test97@test.com', '546153438'),
(14, '+38551365888', 'test66@test.com', '546103438'),
(15, '+38551365887', 'test86@test.com', '546103444');

-- --------------------------------------------------------

--
-- Table structure for table `patient_address`
--

CREATE TABLE `patient_address` (
  `id_ad` int(50) NOT NULL,
  `code` int(8) NOT NULL,
  `city` varchar(40) NOT NULL,
  `address` varchar(60) NOT NULL,
  `medicalNum` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient_address`
--

INSERT INTO `patient_address` (`id_ad`, `code`, `city`, `address`, `medicalNum`) VALUES
(1, 51000, 'Rijeka', 'Riva 10', '123456789'),
(2, 51000, 'Rijeka', 'Pomerio 1', '987654321'),
(3, 51000, 'Rijeka', 'Pomerio 6', '654897321'),
(4, 51345, 'Bakar', 'Breg 3', '321584697'),
(5, 10001, 'Zagreb', 'Test 1', '205614892'),
(6, 51345, 'Bakar', 'Breg 5', '546254154'),
(7, 51345, 'Bakar', 'Breg 9', '105485136'),
(8, 10000, 'Zagreb', 'Breg 9', '105485100'),
(9, 51000, 'Rijeka', 'Street 2', '105875100'),
(10, 51000, 'Rijeka', 'Street 2', '105875180'),
(11, 51000, 'Rijeka', 'Street 8', '105875122'),
(12, 10000, 'Zagreb', 'Street 98', '105875133'),
(13, 10000, 'Zagreb', 'Street 7', '546153438'),
(14, 51345, 'Bakar', 'Street 55', '546103438'),
(15, 51345, 'Bakar', 'Street 999', '546103444');

-- --------------------------------------------------------

--
-- Table structure for table `patient_info`
--

CREATE TABLE `patient_info` (
  `medicalNum` varchar(12) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `blood_group` varchar(3) NOT NULL,
  `gender` varchar(7) NOT NULL,
  `dob` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient_info`
--

INSERT INTO `patient_info` (`medicalNum`, `name`, `surname`, `blood_group`, `gender`, `dob`) VALUES
('105485100', 'Petra', 'Petra', 'A+', 'Female', '1993-12-20'),
('105485136', 'Petra', 'Petra', 'AB-', 'Female', '1993-03-01'),
('105875100', 'Marko', 'Marko', 'B+', 'Male', '1993-12-14'),
('105875122', 'Ivana', 'Marko', 'AB+', 'Female', '1989-12-11'),
('105875133', 'Ivana', 'Mura', 'A+', 'Female', '1989-12-21'),
('105875180', 'Ivan', 'Marko', 'B+', 'Male', '1993-12-14'),
('123456789', 'Luka', 'Luk', 'A+', 'Male', '1983-12-11'),
('205614892', 'Test', 'Tester', 'AB-', 'Male', '2001-07-18'),
('321584697', 'Ivan', 'Mlinar', '0-', 'Male', '2007-10-22'),
('546103438', 'Petar', 'Luka', 'AB+', 'Male', '1989-12-20'),
('546103444', 'Milan', 'Franko', 'AB+', 'Male', '1999-08-18'),
('546153438', 'Petar', 'Mura', 'B-', 'Male', '1989-12-20'),
('546254154', 'Marko', 'Marko', 'AB+', 'Male', '2001-03-18'),
('654897321', 'Marina', 'Horvat', 'AB+', 'Female', '2004-01-18'),
('987654321', 'Iva', 'Horvat', 'AB+', 'Female', '1992-05-19');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contact_info`
--
ALTER TABLE `contact_info`
  ADD PRIMARY KEY (`id_ci`),
  ADD KEY `medNo` (`medicalNum`);

--
-- Indexes for table `patient_address`
--
ALTER TABLE `patient_address`
  ADD PRIMARY KEY (`id_ad`),
  ADD KEY `mediNo` (`medicalNum`);

--
-- Indexes for table `patient_info`
--
ALTER TABLE `patient_info`
  ADD PRIMARY KEY (`medicalNum`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contact_info`
--
ALTER TABLE `contact_info`
  MODIFY `id_ci` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `patient_address`
--
ALTER TABLE `patient_address`
  MODIFY `id_ad` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `contact_info`
--
ALTER TABLE `contact_info`
  ADD CONSTRAINT `medNo` FOREIGN KEY (`medicalNum`) REFERENCES `patient_info` (`medicalNum`) ON UPDATE CASCADE;

--
-- Constraints for table `patient_address`
--
ALTER TABLE `patient_address`
  ADD CONSTRAINT `mediNo` FOREIGN KEY (`medicalNum`) REFERENCES `patient_info` (`medicalNum`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
