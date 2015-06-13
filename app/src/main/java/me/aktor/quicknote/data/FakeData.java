package me.aktor.quicknote.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrea Tortorella on 6/13/15.
 */
public class FakeData {


    private static final String[] TITLES ={
            "Updates to Unity, C++, and iOS tools for Play game services",
            "Android Developer Story: Trello Increases engagement with material design",
            "Introducing new Android training programs with Udacity",
            "Android Design Support Library",
            "Announcing the Material Design Showcase and Awards",
            "A Closer Look at Google Play services 7.5",
            "Empowering successful global businesses on Google Play",
    };

    private static final String[] CONTENT = {
            "To further support all you game developers, we've updated our popular developer tools to give you a consistent set of game services across platforms for a better, more stable experience, with a particular focus on improvements to the Play game services Unity plugin. In addition, we added support for the Nearby Connections API, launched earlier this year at GDC, to our C++ SDK and Unity plugin.\n" +
                    "\n" +
                    "Let’s take a look a closer look!",
            "Trello is a visual collaboration tool that gives teams a shared perspective on projects. It’s built around the concept of a traditional office whiteboard. Simplicity and flexibility are core to the product, so the Trello team recently redesigned their Android app using the material design guidelines to double down on that effort.\n" +
                    "\n" +
                    "According to Fyza Hashim, Designer at Trello, material design had an immediate impact on streamlining app-design and -development at the company. She added that, “Because the guidelines are so thorough and well thought out, you don’t have to go back and forth with developers.”\n" +
                    "\n" +
                    "Sharing is a key component of Trello, so material design helped continue the same cohesive design and intuitive experience on both web and mobile. This makes sharing even easier. As a result, Trello has also seen double digit growth in user engagement with more and more sessions added per week.\n" +
                    "\n" +
                    "Watch the video where we caught up with Michael Pryor, CEO; Hamid Palo, Mobile Lead; and Fyza at the Trello offices in New York to learn more.",
            "We know how important it is for you to efficiently develop the skills to build better Android apps and be successful in your jobs. To meet your training needs, we’ve partnered with Udacity to create Android training courses, ranging from beginner to more advanced content.\n" +
                    "\n" +
                    "Last week at Google I/O we announced the Android Nanodegree, an education credential that is designed for busy people to learn new skills and advance their careers in a short amount of time from anywhere at any time. The nanodegree ties together our Android courses, and provides you with a certificate that may help you be a more marketable Android developer.\n" +
                    "\n" +
                    "Training courses\n" +
                    "All training courses are developed and taught by expert Google instructors from the Developer Platform team. In addition to updating our popular Developing Android Apps course and releasing Advanced Android App Development, we now have courses for everyone from beginning programmers to advanced developers who want to configure their Gradle build settings. And then there's all the fun stuff in between—designing great-looking, high performance apps, making your apps run on watches, TVs, and in cars, and using Google services like Maps, Ads, Analytics, and Fit.\n" +
                    "\n",
            "Android 5.0 Lollipop was one of the most significant Android releases ever, in no small part due to the introduction of material design, a new design language that refreshed the entire Android experience. Our detailed spec is a great place to start to adopt material design, but we understand that it can be a challenge for developers, particularly ones concerned with backward compatibility. With a little help from the new Android Design Support Library, we’re bringing a number of important material design components to all developers and to all Android 2.1 or higher devices. You’ll find a navigation drawer view, floating labels for editing text, a floating action button, snackbar, tabs, and a motion and scroll framework to tie them together.",
            "When we first announced material design in June 2014, we shared an aspirational highlights reel that demonstrated key material principles for motion, interaction, and visual design across a range of hypothetical apps. “Hypothetical” being the key word here—back then, material design was just an idea. Sure, designers and engineers at Google were already working hard on applying material to Google’s Android, iOS, and web apps, but the notion of a single design system that can work across platforms and brands was just an idea.\n" +
                    "\n" +
                    "Fast-forward to today, and thousands of Android apps are adopting material design using the Android 5.0 SDK and AppCompat, while designers and developers begin to experiment with material design on iOS and the web as well. These apps are starting to realize that aspirational vision we set out with that sizzle reel.\n" +
                    "\n" +
                    "Today, we’re celebrating the amazing design work from Google Play developers and announcing the Material Design Showcase and Material Design Awards.\n" +
                    "\n" +
                    "With the Material Design Showcase, we’re highlighting 18 great material design apps through a collection on Google Play, just like with the Beautiful Design collection in years past.\n" +
                    "\n" +
                    "Of those 18 apps, we’re recognizing 6 with a special award, which we handed out during Google I/O today and announced at the Material Now session hosted by Matias Duarte.",
            "At Google I/O, we announced the rollout of Google Play services 7.5 that deliver new capabilities and optimizations to devices across the Android ecosystem. Google Play services ensures that you can build on the latest features from Google for your users, with the confidence that those services will work properly on Android 2.3 and higher devices.\n" +
                    "\n" +
                    "You’ll find the addition of Smart Lock for Passwords, Instance ID, new APIs for Google Cloud Messaging and Google Cast, as well as access to the Google Maps API on Android Wear devices.\n" +
                    "\n" +
                    "Smart Lock for Passwords\n" +
                    "Typing in a password, particularly on a mobile device, is never a pleasant experience. In many cases, your users have already logged in on the web or another device - shouldn’t your login process know that? Smart Lock for Passwords builds on the Chrome Password Manager, adding a new CredentialsApi API and UI on Android to retrieve saved credentials as part of your login process and saving new credentials for later use on other Android devices and any Chrome browser. Both password-based and Identity Provider (IDP, like Google Sign-In) credentials are supported. Keep your users logged in as they move between and to new devices; don’t let them drop off, get frustrated, or end up with multiple accounts.",
            "With more than 50 billion app installs over the past year from users across 190 countries, Google Play continues to see incredible growth thanks to developers like you creating amazing experiences. Play is now reaching more than one billion users every month.\n" +
                    "\n" +
                    "In February, we announced that we had paid out more than $7 billion to developers in the prior year alone. This week at Google I/O, we’re introducing new and powerful tools to help you further grow your business, improve decision making based on smarter insights, and better engage your user base with more relevant content.\n" +
                    "\n" +
                    "Acquire users from the Developer Console\n" +
                    "Once you’ve built a great app, the next important step is to proactively find ways to promote it and grow a loyal user base. App install ads are one powerful way to do that. In the coming months, you’ll be able to quickly and easily set up ad campaigns right from within the Google Play Developer Console for the first time.",

    };

    private static final Random sRand = new Random();

    private static Note generate(){
        int i = sRand.nextInt(Math.min(TITLES.length, CONTENT.length));
        Date date = new Date();
        int l =(int)TimeUnit.DAYS.toMillis(1000);
        date.setTime(date.getTime()-sRand.nextInt(l));
        return new Note(TITLES[i],CONTENT[i],date);
    }


    public static List<Note> generateMany(int n){
        ArrayList<Note> notes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            notes.add(generate());
        }
        return notes;
    }

    public static List<Note> generateAtLeast(int min,int max){
        if (max==min){
            return generateMany(max);
        } else if (max<min) {
            int t = min;
            min = max;
            max = t;
        }
        int i = sRand.nextInt(max - min);
        i+=min;
        return generateMany(i);
    }

}
