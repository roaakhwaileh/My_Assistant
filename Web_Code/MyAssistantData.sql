-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 26, 2021 at 06:41 PM
-- Server version: 10.2.38-MariaDB
-- PHP Version: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jamlnet_myassistant`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `full_name` text NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `photo` text NOT NULL,
  `last_login` text NOT NULL,
  `status` text NOT NULL,
  `role` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `full_name`, `username`, `password`, `photo`, `last_login`, `status`, `role`) VALUES
(3, 'Roaa Kh', 'roaa', '123456', 'https://myassistant.jaml46.net/dashboard/assets/images/logo.png', '26/05/2021 07:39:06 PM', 'active', 'mannager');

-- --------------------------------------------------------

--
-- Table structure for table `assistant`
--

CREATE TABLE `assistant` (
  `assistant_id` int(11) NOT NULL,
  `fullname` text NOT NULL,
  `email` varchar(250) NOT NULL,
  `password` text NOT NULL,
  `phone` text NOT NULL,
  `gender` text NOT NULL,
  `photo` text NOT NULL,
  `birthdate` text NOT NULL,
  `personal_identification` text NOT NULL,
  `disease_freedom` text NOT NULL,
  `status` text NOT NULL,
  `reg_date` text NOT NULL,
  `rating` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `assistant`
--

INSERT INTO `assistant` (`assistant_id`, `fullname`, `email`, `password`, `phone`, `gender`, `photo`, `birthdate`, `personal_identification`, `disease_freedom`, `status`, `reg_date`, `rating`) VALUES
(21, 'sajatawalbeh', 'saja99@gmail.com', '12345678', '55555', 'female', 'https://myassistant.jaml46.net/dashboard/assets/images/assistant/1059886568_1621926776.jpeg', '1999/11/31', 'https://myassistant.jaml46.net/dashboard/assets/images/personal_identification/1354433039_1621926776.jpeg', 'https://myassistant.jaml46.net/dashboard/assets/images/disease_freedom/2064515500_1621926776.jpeg', 'active', '2021-05-25', '4.0');

-- --------------------------------------------------------

--
-- Table structure for table `details_specialty`
--

CREATE TABLE `details_specialty` (
  `assistant_id` int(11) NOT NULL,
  `specialty_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `details_specialty`
--

INSERT INTO `details_specialty` (`assistant_id`, `specialty_id`) VALUES
(21, 1),
(21, 2),
(21, 3),
(21, 4);

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL,
  `title` text NOT NULL,
  `text` text NOT NULL,
  `user_id` text NOT NULL,
  `assistant_id` text NOT NULL,
  `datetime` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`feedback_id`, `title`, `text`, `user_id`, `assistant_id`, `datetime`) VALUES
(37, 'com.google.android.material.textfield.TextInputEditText{409c3a7 VFED..CL. ........ 0,0-660,169 ', '', '', '-1', '2021-05-26'),
(36, 'com.google.android.material.textfield.TextInputEditText{ff43df8 VFED..CL. ........ 0,0-660,169 ', '', '', '-1', '2021-05-26'),
(35, 'com.google.android.material.textfield.TextInputEditText{3985d0c VFED..CL. ........ 0,0-660,169 ', '', '', '-1', '2021-05-26'),
(34, 'com.google.android.material.textfield.TextInputEditText{82a60dd VFED..CL. ........ 0,0-660,169 ', '', '', '-1', '2021-05-26'),
(33, 'com.google.android.material.textfield.TextInputEditText{82a60dd VFED..CL. ........ 0,0-660,169 ', '', '', '-1', '2021-05-26'),
(32, 'com.google.android.material.textfield.TextInputEditText{e96cc6f VFED..CL. ........ 0,0-660,169 ', '', '', '-1', '2021-05-26'),
(31, 'com.google.android.material.textfield.TextInputEditText{4dcdba8 VFED..CL. ........ 0,0-630,194 ', '', '', '-1', '2021-05-25'),
(38, 'fggf', 'fgfg', '', '-1', '2021-05-26'),
(39, 'gfgf', 'fgfg', '', '-1', '2021-05-26'),
(40, 'mjjk', 'l,.l,', '', '-1', '2021-05-26'),
(41, 'my assistant2', 'hi alaa2', '15', '-1', '2021-05-26'),
(42, 'fgfg', 'fgfg', '-1', '21', '2021-05-26'),
(43, 'gg', 'gg', '15', '-1', '2021-05-26'),
(44, 'jj', 'jj', '15', '-1', '2021-05-26'),
(45, 'good', 'good work', '-1', '21', '2021-05-26');

-- --------------------------------------------------------

--
-- Table structure for table `feedback_app`
--

CREATE TABLE `feedback_app` (
  `id_feedback` int(11) NOT NULL,
  `fullname` text NOT NULL,
  `email` text NOT NULL,
  `phone` text NOT NULL,
  `massage` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hourly_work`
--

CREATE TABLE `hourly_work` (
  `hourly_work_id` int(11) NOT NULL,
  `assistant_id` text NOT NULL,
  `specialty_id` text NOT NULL,
  `price` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hourly_work`
--

INSERT INTO `hourly_work` (`hourly_work_id`, `assistant_id`, `specialty_id`, `price`) VALUES
(26, '21', '2', '15'),
(25, '21', '1', '١٣');

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `report_id` int(11) NOT NULL,
  `user_id` text NOT NULL,
  `assistant_id` text NOT NULL,
  `name` text NOT NULL,
  `text` text NOT NULL,
  `send_date` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `req_assistant`
--

CREATE TABLE `req_assistant` (
  `req_assistant_id` int(11) NOT NULL,
  `user_id` text NOT NULL,
  `assistant_id` text NOT NULL,
  `description` text NOT NULL,
  `whatsapp_phone` text NOT NULL,
  `location` text NOT NULL,
  `status` text NOT NULL,
  `specialty_id` text NOT NULL,
  `hourly_work_id` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `req_assistant`
--

INSERT INTO `req_assistant` (`req_assistant_id`, `user_id`, `assistant_id`, `description`, `whatsapp_phone`, `location`, `status`, `specialty_id`, `hourly_work_id`) VALUES
(13, '15', '21', 'Clean', '55555', 'sufie', '-1', '1', '25'),
(11, '15', '21', 'hhh', '0000000', 'hhh', '1', '1', '25'),
(12, '15', '21', 'rr', '0000000', 'dd', '1', '1', '25'),
(10, '15', '21', 'test', '0000000', 'ggg', '1', '1', '25');

-- --------------------------------------------------------

--
-- Table structure for table `slider`
--

CREATE TABLE `slider` (
  `image_id` int(11) NOT NULL,
  `image_url` text NOT NULL,
  `redirect_url` text NOT NULL,
  `added_date` text NOT NULL,
  `status` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `slider`
--

INSERT INTO `slider` (`image_id`, `image_url`, `redirect_url`, `added_date`, `status`) VALUES
(12, 'https://myassistant.jaml46.net/dashboard/assets/images/image_slider/image_slidr_9470692.jpg', 'https://www.google.com/', '23/05/2021 11:27:11 PM', 'active'),
(10, 'https://myassistant.jaml46.net/dashboard/assets/images/image_slider/image_slidr_5435671.png', 'https://www.google.com/', '23/05/2021 10:58:40 PM', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `specialty`
--

CREATE TABLE `specialty` (
  `specialty_id` int(11) NOT NULL,
  `specialty_name` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `specialty`
--

INSERT INTO `specialty` (`specialty_id`, `specialty_name`) VALUES
(1, 'House maid'),
(2, 'Cooker'),
(3, 'Gardener'),
(4, 'Baby Sitter');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `fullname` text NOT NULL,
  `email` varchar(250) NOT NULL,
  `password` text NOT NULL,
  `phone` text NOT NULL,
  `status` text NOT NULL,
  `photo` text NOT NULL,
  `birthdate` text NOT NULL,
  `gender` text NOT NULL,
  `reg_date` text NOT NULL,
  `rating` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `fullname`, `email`, `password`, `phone`, `status`, `photo`, `birthdate`, `gender`, `reg_date`, `rating`) VALUES
(13, 'sajatawalbeh', 'saja99U@gmail.com', '12345678', '000000000', 'active', 'https://myassistant.jaml46.net/dashboard/assets/images/user/1566422422_1621927110.jpeg', '1999/4/25', 'female', '2021-05-25', '0'),
(14, 'sajatawalbeh', 'saja992@gmail.com', '12345678', '000000000', 'active', 'https://myassistant.jaml46.net/dashboard/assets/images/user/1993123431_1621927154.jpeg', '1999/4/25', 'female', '2021-05-25', '0'),
(15, 'sajatawalbeh', 'saja99@gmail.com', '12345678', '000000000', 'active', 'https://myassistant.jaml46.net/dashboard/assets/images/user/1912744359_1621927161.jpeg', '1999/4/25', 'female', '2021-05-25', '2.25');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `assistant`
--
ALTER TABLE `assistant`
  ADD PRIMARY KEY (`assistant_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `details_specialty`
--
ALTER TABLE `details_specialty`
  ADD PRIMARY KEY (`assistant_id`,`specialty_id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedback_id`);

--
-- Indexes for table `feedback_app`
--
ALTER TABLE `feedback_app`
  ADD PRIMARY KEY (`id_feedback`);

--
-- Indexes for table `hourly_work`
--
ALTER TABLE `hourly_work`
  ADD PRIMARY KEY (`hourly_work_id`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`report_id`);

--
-- Indexes for table `req_assistant`
--
ALTER TABLE `req_assistant`
  ADD PRIMARY KEY (`req_assistant_id`);

--
-- Indexes for table `slider`
--
ALTER TABLE `slider`
  ADD PRIMARY KEY (`image_id`);

--
-- Indexes for table `specialty`
--
ALTER TABLE `specialty`
  ADD PRIMARY KEY (`specialty_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `assistant`
--
ALTER TABLE `assistant`
  MODIFY `assistant_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedback_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `feedback_app`
--
ALTER TABLE `feedback_app`
  MODIFY `id_feedback` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `hourly_work`
--
ALTER TABLE `hourly_work`
  MODIFY `hourly_work_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `report_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `req_assistant`
--
ALTER TABLE `req_assistant`
  MODIFY `req_assistant_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `slider`
--
ALTER TABLE `slider`
  MODIFY `image_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `specialty`
--
ALTER TABLE `specialty`
  MODIFY `specialty_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
