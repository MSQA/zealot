[![Build Status](https://secure.travis-ci.org/blinkfox/zealot.svg)](https://travis-ci.org/blinkfox/zealot) [![codecov](https://codecov.io/gh/blinkfox/zealot/branch/master/graph/badge.svg)](https://codecov.io/gh/blinkfox/zealot) [![Maven Central](https://img.shields.io/maven-central/v/com.blinkfox/zealot.svg)](http://search.maven.org/#artifactdetails%7Ccom.blinkfox%7Czealot%7C1.1.4%7Cjar) [![Javadocs](http://www.javadoc.io/badge/com.blinkfox/zealot.svg)](http://www.javadoc.io/doc/com.blinkfox/zealot)

> Zealot是一个轻量级的SQL和参数动态生成工具库，目的是帮助Java开发者书写复杂、动态且易于维护的SQL，它只专注于动态构造和生成SQL，不做SQL执行器，也更不做ORM。所以，能集成到任何ORM、Java DAO工具以及原生的JDBC中。

![Zealot](http://i2.muimg.com/567571/faee17c016c47601.jpg)

## 创建初衷

SQL对开发人员来说是核心的资产之一，在开发中经常需要书写冗长、动态的SQL，许多项目中仅仅采用Java来书写动态SQL，会导致SQL分散、不易调试和阅读。所谓易于维护的SQL应该兼有动态性和可调试性的双重特性。在Java中书写冗长的SQL，虽然能很好的做到动态性，却大大降低了SQL本身的可调试性，开发人员必须运行项目调试打印出SQL才能知道最终的SQL长什么样。所以为了做到可调试性，开发人员开始将SQL单独提取出来存放到配置文件中来维护，这样方便开发人员复制出来粘贴到SQL工具中来直接运行，但无逻辑功能的配置文件虽然解决了可调试性的问题，却又丧失了动态SQL的能力。所以，才不得不诞生出类似于`Mybatis`这样灵活的半ORM工具来解决这两个的问题，但众多的项目却并未集成`mybaits`这样的工具。

[Zealot][1]是基于Java语言开发的SQL及对应有序参数动态拼接生成的工具，其核心设计目标是帮助开发人员书写和生成出具有动态的、可复用逻辑的且易于维护的SQL。为了做到可调试性，就必须将SQL提取到配置文件中来单独维护；为了保证SQL根据某些条件，某些逻辑来动态生成，就必须引入表达式语法或者标签语法来达到动态生成SQL的能力。因此，两者结合才诞生了Zealot。为了便于开发人员书写一般中短长度的动态SQL，zealot还提供了Java流式SQL的书写动态SQL，使SQL可读性和紧凑性更好，如果要书写静态或动态的中、长SQL，则推荐使用xml方式，便于集中阅读、调试和维护SQL。

> **注**：zealot即狂热者，是游戏[星际争霸2][2]中的单位。(My life for Auir!)

## 主要特性

- 轻量级，jar包仅仅46k大小，无副作用的集成和使用
- 提供了纯Java代码和XML两种方式书写维护SQL
- Java的方式采用流式API的方式书写动态SQL，易于书写和阅读
- XML的方式让SQL和Java代码解耦和，易于维护
- 具有动态性、可复用逻辑和可半调试性的优点
- 具有可扩展性，可自定义XML标签和处理器来完成自定义逻辑的SQL和参数生成

  [1]: https://github.com/blinkfox/zealot
  [2]: http://sc2.blizzard.cn/landing
