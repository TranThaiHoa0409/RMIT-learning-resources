# FoAI Week 3 – Tutorial: Prompt Engineering & Custom AI Chatbots

## Outline

1. How to Write Good Prompts for AI Chatbots
2. Create & Vote the Best AI Chatbots

## 1. How to Write Good Prompts for AI Chatbots

### 1.1 Brainstorm: What Makes a Good Prompt?

Work in pairs and brainstorm: which of the two prompts below is better? Share your prompting experience — what makes a good prompt?

- "Write about impact of AI."
- "As an AI researcher, write a 200-word editorial explaining the cognitive impact of AI to college students, in a persuasive and friendly tone."

### 1.2 Elements of a Good Prompt — the PARTS Framework

**PARTS:** Persona, Aim, Recipients, Theme, and Structure.

1. **Persona:** identify your role
2. **Aim:** state your objective
3. **Recipients:** specify the audience
4. **Theme:** describe the style, tone, limitation... of the content of the output
5. **Structure:** note the desired format of the output

Applying PARTS, the prompt above becomes: *"As an AI researcher, write a 200-word editorial explaining the cognitive impact of AI to college students, in a persuasive and friendly tone."*

*Ref: [5 Building Blocks of a Perfect Prompt (Medium)](https://medium.com/innerscorelearningskillstest/5-building-blocks-of-a-perfect-prompt-5d5e6378d122)*
*Other prompting techniques: [promptingguide.ai/techniques/cot](https://promptingguide.ai/techniques/cot)*

### 1.3 Prompt Template with PARTS

```
You are a [Enter your Persona]. [State your Aim and Recipients]. [Describe the content Theme and format Structure].
```

**Example:**

You are a high school history teacher. Design an engaging classroom activity for 11th-grade students focused on creating a collaborative digital timeline about India's struggle for independence. The activity should incorporate storytelling and technology. The time allotment is 3 class periods (60 minutes each). Format this as an outline with bullet points.

*Ref: [5 Building Blocks of a Perfect Prompt (Medium)](https://medium.com/innerscorelearningskillstest/5-building-blocks-of-a-perfect-prompt-5d5e6378d122)*

## 2. Create & Vote the Best AI Chatbots

### 2.1 System Prompts

Chatbots can be customized using System Prompts. Apply PARTS to create good system prompts.

**Examples:**

- You are a psychological counselor. You talk and help people with mental health issues using CBT (Cognitive Behavioral Therapy). Please converse in a friendly manner, showing care and empathy.
- You are an experienced Python programming instructor. You specialize in helping first-year students learn programming through simple and interesting examples. Please use the following book as the main reference material: [book link].

### 2.2 Instructions for Poe

- **Step 1:** Sign up/ Log in [poe.com](https://poe.com)
- **Step 2:** Click "Create"

![Poe — "Create" button](images/Week3/01_poe_create_button.png)

- **Step 3:** Select a bot style (choose "Prompt bot" for this demo)

![Poe — bot style selection panel with "Prompt bot" highlighted](images/Week3/02_poe_bot_style_selection.png)

- **Step 4:** Fulfill bot info (Most important: "Prompt")
- **Step 5:** Publish & Share

### 2.3 Instructions for RMIT Val

- **Step 1:** Log in [val.rmit.edu.au](https://val.rmit.edu.au)
- **Step 2:** Click "My Studio", then click "Create Persona"

![RMIT Val — "My Studio" button](images/Week3/03_rmitval_my_studio_button.png)

![RMIT Val — "Create Persona" button](images/Week3/04_rmitval_create_persona_button.png)

- **Step 3:** Fulfill bot info (Most important: "System Prompt", see detailed guide in "RMIT Val System Prompt")
- **Step 4:** Save & Create

### 2.4 Demo System Prompts

- **System Prompt #1:** You are a Marketing Officer at RMIT Vietnam. Please respond in a friendly and cheerful manner, using emojis. You can speak in both English and Vietnamese.
- **System Prompt #2:** You are a Marketing Officer at RMIT Vietnam. Please respond in a friendly and cheerful manner, using emojis. You can speak in both English and Vietnamese. You ONLY answer questions about RMIT, and nothing else.
- **System Prompt #3:** You are a Marketing Officer at RMIT Vietnam. Please respond in a friendly and cheerful manner, using emojis. You can speak in both English and Vietnamese. You ONLY answer questions about RMIT, and nothing else. You only use information from this website: www.rmit.edu.vn.

### 2.5 Demo Test Prompts

- Hi
- How many campuses does RMIT Vietnam have?
- What is the address of Saigon campus?
- What is the weather like today?
- Can we meet at 7AM?
- Give me phone number

### 2.6 Activities

- **[2 mins]** Tutor divides class into 3–4 groups.
- **[15 mins]** Create and try your team's chatbots. You can use **Poe**, **RMIT Val**, or other platforms (e.g., [huggingface.co/chat](https://huggingface.co/chat), [chatbase.co](https://chatbase.co), [voiceflow.com](https://voiceflow.com)).
- **[2 mins]** Submit your team prompts to [tiny.cc/FOAI-TUT3](https://tiny.cc/FOAI-TUT3) — System prompt + Test prompts demo (screenshots if possible).
- **[3 mins per group]** Showcase your group's chatbot.
- **[2 mins]** Vote for the best/most interesting chatbot: [menti.com/alxmx92f3gef](https://menti.com/alxmx92f3gef)
