# A simple java wrapper library for [alquran-cloud api](https://alquran.cloud/api) 🤍

[![JitPack badge](https://jitpack.io/v/anas-elgarhy/alquran-cloud-api.svg)](https://jitpack.io/#anas-elgarhy/alquran-cloud-api)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=anas-elgarhy_alquran-cloud-api&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=anas-elgarhy_alquran-cloud-api)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=anas-elgarhy_alquran-cloud-api&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=anas-elgarhy_alquran-cloud-api)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=anas-elgarhy_alquran-cloud-api&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=anas-elgarhy_alquran-cloud-api)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=anas-elgarhy_alquran-cloud-api&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=anas-elgarhy_alquran-cloud-api)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=anas-elgarhy_alquran-cloud-api&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=anas-elgarhy_alquran-cloud-api)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=anas-elgarhy_alquran-cloud-api&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=anas-elgarhy_alquran-cloud-api)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=anas-elgarhy_alquran-cloud-api&metric=bugs)](https://sonarcloud.io/summary/new_code?id=anas-elgarhy_alquran-cloud-api)

## It still under development 🚧


## How to add this library into your project

### Maven

**Step 1**. Add the JitPack repository to your build file

```xml

<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```

**Step 2**. Add the dependency

```xml
<dependency>
	<groupId>com.github.anas-elgarhy</groupId>
	<artifactId>alquran-cloud-api</artifactId>
	<version>0.3.0-v1</version>
</dependency>
```

#### Gradle:

**Step 1**. Add the JitPack repository to your build file<br>
*Add it in your root build.gradle at the end of repositories:*

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2**. Add the dependency

```gradle
	dependencies {
	        implementation 'com.github.anas-elgarhy:alquran-cloud-api:0.3.0-v1'
	}
```

## Usage
```java
public class Example1 {
    public static void main(String[] args) throws IOException {
        Ayah ayah = QuranAPI.getAyah(1); // Get the first ayah in the quaran in arabic edition
        System.out.println(ayah.getText());
        System.out.println("***");

        System.out.println("----- آية عشوائية -----");
        Ayah randomAyah = QuranAPI.getRandomAyah(); // Get a random ayah in the quaran in arabic edition
        Surah surah = randomAyah.getSurah(); // Get the surah of the random ayah
        System.out.println(randomAyah.getText() + " - من " + surah.getName());
        System.out.println("***");

        Surah surah2 = QuranAPI.getSurah(1); // Get the first surah in the quaran in arabic edition
        System.out.println(surah2.getName());
        System.out.println("الآيات: ");
        for (Ayah a : surah2.getAyahs()) {
            System.out.println(a.getText() + " (" + a.getNumberInSurah() + ")");
        }
    }
}

```
![Example one output](./Screenshots/example_1_out_0.1.2-v1.png)

```java
public class Example2 {
    public static void main(String[] args) throws IOException {
        Edition[] editions = QuranAPI.getEditions(); // Get all available editions
        System.out.println("The number of available editions: " + editions.length);

        System.out.println("Editions: ");

        for (Edition edition : editions) {
            System.out.println(edition.getName() + " (" + edition.getIdentifier() + ")" +
                    " - Edition type: " + edition.getFormat().toString());
        }
    }
}
```
![Example two output](./Screenshots/example_2_out_0.1.2-v1.gif)

```java
public class Example3 {
    public static void main(String[] args) throws IOException {
        // Get th all available editions in specific language
        Edition[] editionsInEnglish = QuranAPI.getEditions("en");
        System.out.println("The number of available editions in English: " + editionsInEnglish.length);


        // Get th all available editions in specific language and format (audio or text) and type (quran or translation, etc)
        // null means all
        Edition[] editionsInEnglishAudio = QuranAPI.getEditions(EditionFormat.AUDIO, "en", null);
        System.out.println("The number of available editions in English audio: " + editionsInEnglishAudio.length);

        Ayah ayah = QuranAPI.getAyah(1, editionsInEnglishAudio[0]); // Get the first ayah in the quaran in specific edition
        // Becose the audio editions also have the text editions insiw, and usually the text editions are arabic.
        System.out.println(ayah.getText());
        System.out.println(ayah.getAudioUrl()); // Get the audio url of the ayah in 192 kbps.
        // Get the audio urls in other bitrates, returns same url if no other bitrates.
        System.out.println(Arrays.toString(ayah.getSecondaryAudioUrls()));

        Edition edition = editionsInEnglish[0];
        System.out.println(edition.getName());
        System.out.println(edition.getType());
        System.out.println(edition.getFormat());

        Ayah ayahFromEdition = QuranAPI.getAyah(1, edition); // Get the first ayah in the quaran in specific edition
        System.out.println(ayahFromEdition.getText());

        // Get the first surah in the quaran in specific edition
        Surah fistSurah = QuranAPI.getSurah(1, edition);
        System.out.println(fistSurah.getName());

        // Or you can use the Surah enum to get the surah:
        Surah s = QuranAPI.getSurah(Surahs.AL_ALAQ);
        System.out.println(s.getName());
    }
}
```
![Example three output](./Screenshots/example_3_out_0.2.0-v1.png)

## Requirements for development:
- Maven
- jdk 17
- IntelliJ IDEA (not required but recommended)


### Available in

[![GitHub](https://img.shields.io/badge/GitHub-Main%20repo-brightgreen?style=for-the-badge&logo=GitHub)](https://github.com/anas-elgarhy/alquran-cloud-api)
[![GitLab](https://img.shields.io/badge/GitLab-Mirror%20repo-brightgreen?style=for-the-badge&logo=GitLab)](https://gitlab.com/anas-elgarhy/alquran-cloud-api)
[![BitBucket](https://img.shields.io/badge/BitBucket-Mirror%20repo-brightgreen?style=for-the-badge&logo=BitBucket)](https://bitbucket.org/anas_elgarhy/alquran-cloud-api)
[![Codeberg](https://img.shields.io/badge/Codeberg-Mirror%20repo-brightgreen?style=for-the-badge&logo=Codeberg)](https://codeberg.org/anas-elgarhy/alquran-cloud-api)

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=anas-elgarhy_alquran-cloud-api)](https://sonarcloud.io/summary/new_code?id=anas-elgarhy_alquran-cloud-api)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/summary/new_code?id=anas-elgarhy_alquran-cloud-api)
