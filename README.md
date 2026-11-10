# Лаб 9: Код хамралт — JaCoCo

Оюутан: Ган-Оргил  
Код: B232270091

## Орчин

`java -version`:

```
openjdk version "17.0.20.1" 2026-08-18
OpenJDK Runtime Environment Temurin-17.0.20.1+1 (build 17.0.20.1+1)
OpenJDK 64-Bit Server VM Temurin-17.0.20.1+1 (build 17.0.20.1+1, mixed mode, sharing)
```

`mvn -version`:

```
Apache Maven 3.9.12 (848fbb4bf2d427b72bdb2471c22fced7ebd9a7a1)
Maven home: C:\Users\enhta\Desktop\New folder\.lab8-work\tools\apache-maven-3.9.12
Java version: 17.0.20.1, vendor: Eclipse Adoptium, runtime: C:\Users\enhta\Desktop\New folder\.lab8-work\tools\jdk-17.0.20.1+1
Default locale: en_US, platform encoding: UTF-8
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

(Гаралтууд `results/java-version.txt`, `results/mvn-version.txt`-д бий.)

## A. Happy path тестүүд ба эхний хэмжилт

5 happy-path тест: `letterGrade_95_isA`, `letterGrade_85_isB`, `gpa_95_85_is3_5`,
`scholarship_gpa3_9_credits16_noViolation_is100`, `scholarship_gpa3_5_credits12_noViolation_is70`
(`results/mvn-verify-A.txt`: `Tests run: 5, Failures: 0`).

`results/jacoco-A.csv`-аас:

| Хэмжүүр | Covered | Missed | Хамралт |
|---|---|---|---|
| Line | 15 | 10 | 15/25 = 60.0% |
| Branch | 20 | 21 | 20/41 ≈ 48.8% |

**Ажиглалт.** Улаан (огт хүрээгүй) мөрүүд нь бүгд алдааны болон "доод" замууд: гурван
`throw new IllegalArgumentException` мөр (21, 32, 49), `letterGrade`-ийн C/D/F буцаалт (25–27),
`gpa`-ийн `case "C"`/`case "D"` (39–40), `scholarship`-ийн `return 50` ба сүүлийн `return 0` (54–55).
Шар ромботой мөрүүд нь нөхцлийн зөвхөн нэг талыг шалгасан газрууд: мөр 20, 31, 48-ийн `||`
нөхцлүүдийн "үнэн" тал хэзээ ч биелээгүй, мөр 51-д `hasViolation == true` тест байхгүй, мөр 52–53-ийн
`&&`-ийн "голч хангасан ч кредит дутуу" салаа, мөр 36-гийн `switch`-ийн C/D/default салаанууд дутуу.
