/*
 Navicat Premium Data Transfer

 Source Server         : MyDB
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : localhost:3306
 Source Schema         : zj

 Target Server Type    : MySQL
 Target Server Version : 50712
 File Encoding         : 65001

 Date: 22/04/2019 22:45:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for applicationquestion
-- ----------------------------
DROP TABLE IF EXISTS `applicationquestion`;
CREATE TABLE `applicationquestion`  (
                                      `id` int(11) NOT NULL AUTO_INCREMENT,
                                      `questiontypeid` int(11) NULL DEFAULT NULL,
                                      `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `chapter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of applicationquestion
-- ----------------------------
INSERT INTO `applicationquestion` VALUES (1, 13, '数据结构是一门研究什么内容的学科？', '数据结构是一门研究在非数值计算的程序设计问题中，计算机的操作对象及对象间的关系和施加于对象的操作等的学科。', '/appimg/3.png', '第一章');
INSERT INTO `applicationquestion` VALUES (2, 14, '撒大苏打', '打算大苏打', '/appimg/2.png', '第二章');
INSERT INTO `applicationquestion` VALUES (3, 15, '2121', '212', '/appimg/3.png', '第二章');
INSERT INTO `applicationquestion` VALUES (4, 15, '212', '12121', '/appimg/1.png', '第二章');
INSERT INTO `applicationquestion` VALUES (8, 15, '啊大苏打', '按时大苏打asdasdas', '/appimg/8.jpg', '第二章');
INSERT INTO `applicationquestion` VALUES (10, 14, '212', '2121', '/appimg/2.png', '第一章');

-- ----------------------------
-- Table structure for choicequestion
-- ----------------------------
DROP TABLE IF EXISTS `choicequestion`;
CREATE TABLE `choicequestion`  (
                                 `id` int(11) NOT NULL AUTO_INCREMENT,
                                 `questiontypeid` int(11) NULL DEFAULT NULL,
                                 `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                 `option1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                 `option2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                 `option3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                 `option4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                 `option5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                 `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                 `chapter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of choicequestion
-- ----------------------------
INSERT INTO `choicequestion` VALUES (1, 1, '算法的计算量的大小称为计算的（    ）。', 'A.效率  ', 'B. 复杂性 ', 'C. 现实性', 'D. 难度', '', 'B', '第二章');
INSERT INTO `choicequestion` VALUES (2, 1, '算法的时间复杂度取决于（ ）。', 'A.问题的规模', 'B. 待处理数据的初态', 'C. A和B', '', '', 'C', '第二章');
INSERT INTO `choicequestion` VALUES (3, 1, '下面关于算法说法错误的是（    ）。', 'A.算法最终必须由计算机程序实现', 'B.为解决某问题的算法同为该问题编写的程序含义是相同的', 'C. 算法的可行性是指指令不能有二义性', 'D. 以上几个都是错误的', '', 'D', '第二章');
INSERT INTO `choicequestion` VALUES (8, 2, '一个算法应该是（     ）。', 'A．程序', 'B．问题求解步骤的描述', 'C．要满足五个基本特性', 'D．A和C', '', 'B', '第二章');
INSERT INTO `choicequestion` VALUES (9, 1, '下面关于算法说法错误的是（    ）。', 'A．算法最终必须由计算机程序实现', 'B.为解决某问题的算法同为该问题编写的程序含义是相同的', 'C. 算法的可行性是指指令不能有二义性', 'D. 以上几个都是错误的', '', 'D', '第二章');
INSERT INTO `choicequestion` VALUES (10, 1, '下面说法错误的是（    ）\r\n\r\n    (1）算法原地工作的含义是指不需要任何额外的辅助空间\r\n\r\n   （2）在相同的规模n下，复杂度O(n)的算法在时间上总是优于复杂度O(2n)的算法\r\n\r\n   （3）所谓时间复杂度是指最坏情况下，估算算法执行时间的一个上界\r\n\r\n   （4）同一个算法，实现语言的级别越高，执行效率就越低', 'A．(1)', 'B.(1),(2)', 'C.(1),(4)', 'D.(3)', '', 'C', '第二章');
INSERT INTO `choicequestion` VALUES (11, 3, '从逻辑上可以把数据结构分为（    ）两大类。', 'A．动态结构、静态结构', 'B．顺序结构、链式结构', 'C．线性结构、非线性结构', 'D．初等结构、构造型结构', '', 'C', '第二章');
INSERT INTO `choicequestion` VALUES (12, 2, '以下与数据的存储结构无关的术语是（    ）。', 'A．循环队列', 'B. 链表', 'C. 哈希表', 'D.  栈', '', 'D', '第二章');
INSERT INTO `choicequestion` VALUES (13, 1, '以下数据结构中，哪一个是线性结构（    ）？', 'A．广义表', 'B. 二叉树', 'C. 稀疏矩阵', 'D.  串', '', 'D', '第二章');
INSERT INTO `choicequestion` VALUES (14, 3, '以下那一个术语与数据的存储结构无关？（    ）', 'A．栈', 'B. 哈希表', 'C. 线索树', 'D.  双向链表', '', 'A', '第二章');
INSERT INTO `choicequestion` VALUES (15, 3, '以下哪个数据结构不是多型数据类型（    ）', 'A．栈', 'B．广义表', 'C．有向图', 'D．字符串', '', 'D', '第二章');
INSERT INTO `choicequestion` VALUES (16, 2, '以下数据结构中，（    ）是非线性数据结构', 'A．树', 'B．字符串', 'C．队', 'D．栈', '', 'A', '第二章');
INSERT INTO `choicequestion` VALUES (17, 1, '下列数据中，（    ）是非线性数据结构。', 'A．栈', 'B.  队列', 'C.  完全二叉树', 'D. 堆', '', 'C', '第二章');
INSERT INTO `choicequestion` VALUES (18, 1, '连续存储设计时，存储单元的地址（    ）。', 'A．一定连续', 'B．一定不连续', 'C．不一定连续', 'D．部分连续，部分不连续', '', 'A', '第二章');
INSERT INTO `choicequestion` VALUES (19, 1, '以下属于逻辑结构的是（    ）。', 'A．顺序表', 'B. 哈希表 ', 'C.有序表 ', 'D.  单链表', '', 'C', '第二章');
INSERT INTO `choicequestion` VALUES (20, 3, '下述哪一条是顺序存储结构的优点？（    ）', 'A．存储密度大', 'B．插入运算方便', 'C．删除运算方便', 'D．可方便地用于各种逻辑结构的存储表示', '', 'A', '第二章');
INSERT INTO `choicequestion` VALUES (21, 2, '下面关于线性表的叙述中，错误的是哪一个？（    ）', 'A．线性表采用顺序存储，必须占用一片连续的存储单元。', 'B．线性表采用顺序存储，便于进行插入和删除操作。', 'C．线性表采用链接存储，不必占用一片连续的存储单元。', 'D．线性表采用链接存储，便于插入和删除操作。', '', 'B', '第二章');
INSERT INTO `choicequestion` VALUES (22, 3, '线性表是具有n个（    ）的有限序列（n>0）。', 'A．表元素', 'B．字符', 'C．数据元素', 'D．数据项', ' E．信息项', 'C', '第二章');
INSERT INTO `choicequestion` VALUES (23, 1, '若某线性表最常用的操作是存取任一指定序号的元素和在最后进行插入和删除运算，则利用（    ）存储方式最节省时间。', 'A．顺序表', 'B．双链表', 'C．带头结点的双循环链表', 'D．单循环链表', '', 'A', '第二章');
INSERT INTO `choicequestion` VALUES (24, 2, '某线性表中最常用的操作是在最后一个元素之后插入一个元素和删除第一个元素，则采用（    ）存储方式最节省运算时间。', 'A．单链表', ' B．仅有头指针的单循环链表', 'C．双链表', ' D．仅有尾指针的单循环链表', '', 'D', '第二章');

-- ----------------------------
-- Table structure for completion
-- ----------------------------
DROP TABLE IF EXISTS `completion`;
CREATE TABLE `completion`  (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `questiontypeid` int(11) NULL DEFAULT NULL,
                             `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `chapter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of completion
-- ----------------------------
INSERT INTO `completion` VALUES (1, 10, '数据的物理结构包括_______的表示和_______的表示。', '数据元素,数据元素间关系', '第二章');
INSERT INTO `completion` VALUES (2, 10, '对于给定的n个元素,可以构造出的逻辑结构有_______,_______,_______,_______四种。', '12,212,212,212', '第二章');
INSERT INTO `completion` VALUES (5, 12, '数据的逻辑结构是指_______。', '数据的组织形式，即数据元素之间逻辑关系的总体。而逻辑关系是指数据元素之间的关联方式或称“邻接关系”', '第二章');
INSERT INTO `completion` VALUES (6, 11, '一个数据结构在计算机中__________称为存储结构。', '表示(又称映像)', '第二章');
INSERT INTO `completion` VALUES (7, 11, '抽象数据类型的定义仅取决于它的一组__（1）_，而与_（2）_无关，即不论其内部结构如何变化，只要它的_（3）_不变，都不影响其外部使用。', '逻辑特性,在计算机内部如何表示和实现,数学特性', '第二章');
INSERT INTO `completion` VALUES (8, 10, '数据结构中评价算法的两个重要指标是_______。          ', '算法的时间复杂度和空间复杂度', '第二章');
INSERT INTO `completion` VALUES (9, 10, '数据结构是研讨数据的_（1）_和_（2）_，以及它们之间的相互关系，并对与这种结构定义相应的_（3）_，设计出相应的（4）_。', '逻辑结构,物理结构,操作（运算）,算法', '第二章');
INSERT INTO `completion` VALUES (10, 10, ' 一个算法具有5个特性: （1） 、 （2） 、 （3） ，有零个或多个输入、有一个或多个输出 。', '有穷性,确定性,可行性。', '第二章');
INSERT INTO `completion` VALUES (11, 10, '已知如下程序段\r\n\r\nFOR i:= n  DOWNTO  1  DO         {语句1}\r\n\r\nBEGIN\r\n\r\nx:=x+1；                      {语句2}\r\n\r\nFOR j:=n  DOWNTO  i  DO   {语句3}\r\n\r\n y:=y+1;                     {语句4}\r\n\r\nEND；\r\n\r\n语句1执行的频度为 （1） ；语句2执行的频度为 （2） ；语句3执行的频度为 （3） ；语句4执行的频度为 （4） 。', 'n+1,n,n(n+3)/2,n(n+1)/2', '第二章');
INSERT INTO `completion` VALUES (12, 11, '在下面的程序段中，对ｘ的赋值语句的频度为______（表示为n的函数）\r\n\r\n   FOR　　i：＝１ TO  n　DO　\r\n\r\n　　FOR　　j：＝１　TO  i　DO\r\n\r\n　FOR　k：＝1　TO　j　DO　\r\n\r\nｘ：＝ｘ＋delta；', '1+（1+2++（1+2+3）+…+（1+2+…+n）=n(n+1)(n+2)/6', '第一章');

-- ----------------------------
-- Table structure for designproblem
-- ----------------------------
DROP TABLE IF EXISTS `designproblem`;
CREATE TABLE `designproblem`  (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `questiontypeid` int(11) NULL DEFAULT NULL,
                                `chapter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of designproblem
-- ----------------------------
INSERT INTO `designproblem` VALUES (1, '在数据结构课程中，数据的逻辑结构，数据的存储结构及数据的运算之间存在着怎样的关系？', '数据的逻辑结构反映数据元素之间的逻辑关系（即数据元素之间的关联方式或“邻接关系”），数据的存储结构是数据结构在计算机中的表示，包括数据元素的表示及其关系的表示。数据的运算是对数据定义的一组操作，运算是定义在逻辑结构上的，和存储结构无关，而运算的实现则是依赖于存储结构。', 7, '第二章');
INSERT INTO `designproblem` VALUES (2, '若逻辑结构相同但存储结构不同，则为不同的数据结构。这样的说法对吗？举例说明之', '逻辑结构相同但存储不同，可以是不同的数据结构。例如，线性表的逻辑结构属于线性结构，采用顺序存储结构为顺序表，而采用链式存储结构称为线性链表。', 7, '第二章');
INSERT INTO `designproblem` VALUES (3, '在给定的逻辑结构及其存储表示上可以定义不同的运算集合，从而得到不同的数据结构。这样说法对吗？举例说明之。', '栈和队列的逻辑结构相同，其存储表示也可相同（顺序存储和链式存储），但由于其运算集合不同而成为不同的数据结构。', 7, '第二章');
INSERT INTO `designproblem` VALUES (4, '评价一个好的算法，您是从哪几方面来考虑的？', '评价好的算法有四个方面。一是算法的正确性；二是算法的易读性；三是算法的健壮性；四是算法的时空效率（运行）。', 7, '第二章');
INSERT INTO `designproblem` VALUES (5, '根据数据元素之间的逻辑关系，一般有哪几类基本的数据结构？', '集合、线性结构、树形结构、图形或网状结构。', 8, '第二章');
INSERT INTO `designproblem` VALUES (6, '当你为解决某一问题而选择数据结构时，应从哪些方面考虑？', '通常考虑算法所需要的存储空间量和算法所需要的时间量。后者又涉及到四方面：程序运行时所需输入的数据总量，对源程序进行编译所需时间，计算机执行每条指令所需时间和程序中指令重复执行的次数。', 9, '第二章');
INSERT INTO `designproblem` VALUES (7, '若将数据结构定义为一个二元组（D，R）,说明符号D，R 应分别表示什么？', 'D是数据元素的有限集合，S是D上数据元素之间关系的有限集合。', 8, '第二章');
INSERT INTO `designproblem` VALUES (8, '数据结构与数据类型有什么区别？', '“数据结构”这一术语有两种含义，一是作为一门课程的名称；二是作为一个科学的概念。作为科学概念，目前尚无公认定义，一般认为，讨论数据结构要包括三个方面，一是数据的逻辑结构，二是数据的存储结构，三是对数据进行的操作（运算）。而数据类型是值的集合和操作的集合，可以看作是已实现了的数据结构，后者是前者的一种简化情况。', 9, '第二章');
INSERT INTO `designproblem` VALUES (9, '线性表的顺序存储结构具有三个弱点：其一，在作插入或删除操作时，需移动大量元素；其二，由于难以估计，必须预先分配较大的空间，往往使存储空间不能得到充分利用；其三，表的容量难以扩充。线性表的链式存储结构是否一定都能够克服上述三个弱点，试讨论之。', '链式存储结构一般说克服了顺序存储结构的三个弱点。首先，插入、删除不需移动元素，只修改指针，时间复杂度为O(1)；其次，不需要预先分配空间，可根据需要动态申请空间；其三，表容量只受可用内存空间的限制。其缺点是因为指针增加了空间开销，当空间不允许时，就不能克服顺序存储的缺点。', 7, '第二章');
INSERT INTO `designproblem` VALUES (10, '说明在线性表的链式存储结构中，头指针与头结点之间的根本区别；头结点与首元结点的关系。', '在线性表的链式存储结构中，头指针指链表的指针，若链表有头结点则是链表的头结点的指针，头指针具有标识作用，故常用头指针冠以链表的名字。头结点是为了操作的统一、方便而设立的，放在第一元素结点之前，其数据域一般无意义（当然有些情况下也可存放链表的长度、用做监视哨等等），有头结点后，对在第一元素结点前插入结点和删除第一结点，其操作与对其它结点的操作统一了。而且无论链表是否为空，头指针均不为空。首元结点也就是第一元素结点，它是头结点后边的第一个结点。', 9, '第二章');

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `studentname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `stuid` int(11) NULL DEFAULT NULL,
                           `paperid` int(11) NULL DEFAULT NULL,
                           `cqanswer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `cpanswer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `dpanswer` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                           `jqanswer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `apanswer` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                           `state` int(11) NULL DEFAULT NULL,
                           `evaluate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `proposal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `dotype` int(11) NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES (3, '王六', '计科1540', 2121, 2, 'B;B;B;B', '44,asdas11', '对于相同的大苏打大;2323;EQE', 'T;F;T', '12121', 1, 'adasda', 'sasasas', '5.0', 0);
INSERT INTO `homework` VALUES (6, '张三', '计科1501', 1521026, 1, 'A;A;C;C;C', 'wqwq,212;aczxc,1321', '对于相同的zxc;asedased;', 'T;F;F', 'wqewqeqw', 0, '', '', NULL, 0);
INSERT INTO `homework` VALUES (10, '张三', '计科1501', 1521026, 14, 'A;A;C;D;D;D;D;D;D', 'ASA,212,122,212;2121', 'SFSDFS;DSFSDF', 'T;T', 'ADASD', 0, NULL, NULL, NULL, 0);
INSERT INTO `homework` VALUES (11, '张三', '计科1501', 1521026, 14, 'A;A;C;D;D;D;D;D;D', 'ASA,212,122,212;2121', 'SFSDFS;DSFSDF', 'T;T', 'ADASD', 0, NULL, NULL, NULL, 0);
INSERT INTO `homework` VALUES (12, '张三', '计科1501', 1521026, 14, 'A;A;C;D;D;D;D;D;D', 'ASA,212,122,212;2121', 'SFSDFS;DSFSDF', 'T;T', 'ADASD', 0, NULL, NULL, NULL, 0);
INSERT INTO `homework` VALUES (15, '张三', '软件工程1501', 1521026, 18, 'B;A;D;A;C;A;B;B;C;A;C;A;A;B;B;A;E;B;B', 'qewqwe,eqweq;eqwe,ewqeq,eqweq,eqwe;dasdasda;safdsfsd;dasda', 'dasdasdas', 'F;T;T;F;T;F;T', 'dasdasdasda', 0, NULL, NULL, NULL, 0);
INSERT INTO `homework` VALUES (16, '张三', '软件工程1501', 1521026, 1, 'A;B;B;C;B', 'dada,212;eqeq,eqweq,eqweq,eqweq', 'dasdasdasdas;asdadada;dasdada', 'F;F;T', 'dasdasdasdasd', 1, NULL, NULL, '2.0', 1);

-- ----------------------------
-- Table structure for judgementquestion
-- ----------------------------
DROP TABLE IF EXISTS `judgementquestion`;
CREATE TABLE `judgementquestion`  (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `questiontypeid` int(11) NULL DEFAULT NULL,
                                    `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `chapter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of judgementquestion
-- ----------------------------
INSERT INTO `judgementquestion` VALUES (1, 4, '算法的优劣与算法描述语言无关，但与所用计算机有关', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (2, 4, '健壮的算法不会因非法的输入数据而出现莫名其妙的状态', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (3, 4, '算法可以用不同的语言描述，如果用C 语言或PASCAL语言等高级语言来描述，则算法实际上就是程序了', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (8, 4, '数据的物理结构是指数据在计算机内的实际存储形式。', 'T', '第二章');
INSERT INTO `judgementquestion` VALUES (9, 6, '数据元素是数据的最小单位。', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (10, 5, '记录是数据处理的最小单位。', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (11, 4, '数据的逻辑结构是指数据的各数据项之间的逻辑关系', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (12, 6, '数据结构的抽象操作的定义与具体实现有关。', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (13, 5, '在顺序存储结构中，有时也存储数据结构中元素之间的关系。', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (14, 5, '顺序存储方式的优点是存储密度大，且插入、删除运算效率高。', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (15, 6, '数据结构的基本操作的设置的最重要的准则是，实现应用程序与存储结构的独立。', 'T', '第二章');
INSERT INTO `judgementquestion` VALUES (16, 4, '数据的逻辑结构说明数据元素之间的顺序关系,它依赖于计算机的储存结构.', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (17, 4, '链表中的头结点仅起到标识的作用。', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (18, 5, '顺序存储结构的主要缺点是不利于插入或删除操作。', 'T', '第二章');
INSERT INTO `judgementquestion` VALUES (19, 4, '线性表采用链表存储时，结点和结点内部的存储空间可以是不连续的。', 'T', '第二章');
INSERT INTO `judgementquestion` VALUES (20, 6, '顺序存储方式插入和删除时效率太低，因此它不如链式存储方式好。', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (21, 5, '对任何数据结构链式存储结构一定优于顺序存储结构。', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (22, 4, '顺序存储方式只能用于存储线性结构。', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (23, 6, '集合与线性表的区别在于是否按关键字排序。', 'F', '第二章');
INSERT INTO `judgementquestion` VALUES (24, 6, '所谓静态链表就是一直不发生变化的链表。', 'F', '第二章');

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES (1, 'xiaoming', '123456');
INSERT INTO `login` VALUES (2, 'xiaowang', '123456');
INSERT INTO `login` VALUES (3, 'genmiao', '123456');
INSERT INTO `login` VALUES (4, 'zhangsan', '123456');
INSERT INTO `login` VALUES (5, 'lisi', '123456');
INSERT INTO `login` VALUES (6, 'admin', '123456');
INSERT INTO `login` VALUES (7, 'aaaaaa', '123456');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (1, '软件工程');
INSERT INTO `major` VALUES (2, '数据结构');
INSERT INTO `major` VALUES (3, '计科');
INSERT INTO `major` VALUES (4, '物联网');

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        `cq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        `cp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        `dp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        `jq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        `aq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES (1, '测试001', '1,2,3,9,11', '1,2,3', '1,2,3', '1,2,3', '1', '58.0');
INSERT INTO `paper` VALUES (2, '测试002', '1,3,8,10', '1,3,2', '3,2,1', '2,1,3', '1', '56.0');
INSERT INTO `paper` VALUES (12, 'sadasda', '2,11', '5,2,1', '8', '2', '1', '50.0');
INSERT INTO `paper` VALUES (13, '人工选题001', '1,3,8', '2,5', '1,7', '2,3', '1', '58.0');
INSERT INTO `paper` VALUES (14, '自动选题002', '28,19,1,13,21,2,12,20,9,16', '2,5', '1,7', '14,21', '1', '62.0');
INSERT INTO `paper` VALUES (15, '自动选题003', '23,18,13,11,20', '2', '1,4', '8', '1', '46.0');
INSERT INTO `paper` VALUES (16, 'xcfzxc', '24,22,20,13,14,12,3,23,8,18', '9,12,7,11,2', '9,10', '11,22,24,19,15', '2,4', '110.0');
INSERT INTO `paper` VALUES (17, '1212', '12', '5', '4,9,10', '22,8,23', '4,2,3', '100.0');
INSERT INTO `paper` VALUES (18, '20190417', '1,2,3,8,9,10,11,12,13,14,15,16,17,18,20,21,22,23,24', '12,1,2,5,6', '1', '1,2,3,8,9,10,14', '1', '100.0');

-- ----------------------------
-- Table structure for questiontype
-- ----------------------------
DROP TABLE IF EXISTS `questiontype`;
CREATE TABLE `questiontype`  (
                               `id` int(10) NOT NULL AUTO_INCREMENT,
                               `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                               `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                               `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of questiontype
-- ----------------------------
INSERT INTO `questiontype` VALUES (1, '选择题', '简单', '2');
INSERT INTO `questiontype` VALUES (2, '选择题', '一般', '2');
INSERT INTO `questiontype` VALUES (3, '选择题', '困难', '2');
INSERT INTO `questiontype` VALUES (4, '判断题', '简单', '1');
INSERT INTO `questiontype` VALUES (5, '判断题', '一般', '1');
INSERT INTO `questiontype` VALUES (6, '判断题', '困难', '1');
INSERT INTO `questiontype` VALUES (7, '简答题', '简单', '10');
INSERT INTO `questiontype` VALUES (8, '简答题', '一般', '10');
INSERT INTO `questiontype` VALUES (9, '简答题', '困难', '10');
INSERT INTO `questiontype` VALUES (10, '填空题', '简单', '5');
INSERT INTO `questiontype` VALUES (11, '填空题', '一般', '5');
INSERT INTO `questiontype` VALUES (12, '填空题', '困难', '5');
INSERT INTO `questiontype` VALUES (13, '应用题', '简单', '20');
INSERT INTO `questiontype` VALUES (14, '应用题', '一般', '20');
INSERT INTO `questiontype` VALUES (15, '应用题', '困难', '20');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                          `loginid` int(11) NULL DEFAULT NULL,
                          `majorid` int(255) NULL DEFAULT NULL,
                          `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                          `stuid` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '张三', 4, 1, '1501', '1521026');
INSERT INTO `student` VALUES (2, '李四', 5, 1, '1501', '1521027');
INSERT INTO `student` VALUES (6, '王五', NULL, 3, '1502', '1521028');
INSERT INTO `student` VALUES (7, '张柳', NULL, 1, '1501', '1521029');
INSERT INTO `student` VALUES (8, '孙琦', NULL, 3, '1502', '1521030');
INSERT INTO `student` VALUES (9, '赵霸', NULL, 1, '1501', '1521031');
INSERT INTO `student` VALUES (10, '林子祥', NULL, 3, '1502', '1521032');
INSERT INTO `student` VALUES (11, '莫文蔚', NULL, 4, '1503', '1521033');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `teaid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                          `majorid` int(11) NULL DEFAULT NULL,
                          `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                          `roleid` int(11) NULL DEFAULT NULL,
                          `loginid` int(11) NULL DEFAULT NULL,
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '1511', 1, '王华军', 0, 1);
INSERT INTO `teacher` VALUES (2, '1522', 1, '王三', 0, 2);
INSERT INTO `teacher` VALUES (3, '1533', 1, '更妙', 1, 3);
INSERT INTO `teacher` VALUES (4, '1633', 1, '元龙品', 0, 7);

SET FOREIGN_KEY_CHECKS = 1;
