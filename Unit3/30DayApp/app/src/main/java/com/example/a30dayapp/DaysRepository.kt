package com.example.a30dayapp

import com.example.a30dayapp.model.Day
import java.lang.Character.toString

object DaysRepository {
    val titleList = listOf(
        "Introduction to Mindfulness",
        "Deep Breathing Exercises",
        "Body Scan Meditation",
        "Gratitude Journaling",
        "Mindful Eating",
        "Walking Meditation",
        "Loving-Kindness Meditation",
        "Progressive Muscle Relaxation",
        "Visualization Techniques",
        "Mindful Listening",
        "Digital Detox",
        "Yoga for Mindfulness",
        "Mindful Stretching",
        "Nature Connection",
        "Self-Compassion Practices",
        "Mindful Productivity",
        "Journaling for Clarity",
        "Mindful Communication",
        "Setting Intentions",
        "Mindful Creativity",
        "Stress Reduction Techniques",
        "Mindful Relationships",
        "Sleep Hygiene Practices",
        "Mindful Technology Use",
        "Emotional Awareness",
        "Mindful Goal Setting",
        "Cultivating Patience",
        "Mindful Parenting",
        "Reflecting on Progress",
        "Creating a Sustainable Practice"
    )

    val descriptionList = listOf(
        "Learn the basics of mindfulness and how to incorporate it into your daily life.",
        "Practice deep breathing techniques to calm your mind and body.",
        "Engage in a full-body scan meditation to enhance body awareness.",
        "Start a gratitude journal to focus on positive aspects of your life.",
        "Explore mindful eating habits to improve your relationship with food.",
        "Take a mindful walk, paying attention to your surroundings and sensations.",
        "Practice loving-kindness meditation to cultivate compassion for yourself and others.",
        "Learn progressive muscle relaxation techniques to reduce physical tension.",
        "Use visualization techniques to create a peaceful mental space.",
        "Enhance your listening skills through mindful listening exercises.",
        "Take a break from digital devices to reconnect with the present moment.",
        "Incorporate yoga poses that promote mindfulness and relaxation.",
        "Engage in gentle stretching exercises with mindful awareness.",
        "Connect with nature and practice mindfulness outdoors.",
        "Cultivate self-compassion through specific mindfulness practices.",
        "Improve productivity by applying mindfulness techniques to your work.",
        "Use journaling as a tool for gaining clarity and insight.",
        "Practice mindful communication to enhance your interactions with others.",
        "Set clear intentions for your mindfulness practice and daily life.",
        "Engage in creative activities mindfully to foster innovation and joy.",
        "Learn techniques to manage stress through mindfulness practices.",
        "Apply mindfulness principles to improve your relationships.",
        "Adopt sleep hygiene practices that promote restful sleep.",
        "Use technology mindfully to reduce distractions and enhance focus.",
        "Increase emotional awareness through mindfulness exercises.",
        "Set achievable goals using mindfulness strategies for better focus.",
        "Cultivate patience through specific mindfulness practices.",
        "Incorporate mindfulness into parenting for better family dynamics.",
        "Reflect on your progress and experiences over the past month.",
        "Create a sustainable mindfulness practice that fits your lifestyle."
    )

    val imageList = listOf(
        R.drawable._249263074_8f46ed271b_k,
        R.drawable._414791929_757161dc4d_k,
        R.drawable._1677860567_edf1b77a64_k,
        R.drawable._2280569835_731a0b5348_k,
        R.drawable._3884915418_82bbb4fb0e_k,
    )

    val dayList = List(titleList.size) { i ->
        Day(
            dayID = "${i + 1}",
            title = titleList[i],
            description = descriptionList[i],
            imageRes = imageList.random()
        )
    }
}