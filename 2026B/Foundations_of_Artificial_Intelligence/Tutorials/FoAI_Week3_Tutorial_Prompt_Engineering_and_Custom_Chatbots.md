# FoAI Week 3 – Tutorial: Prompt Engineering & Custom AI Chatbots

## 1. How to Write Good Prompts for AI Chatbots

### 1.1 Brainstorm: What Makes a Good Prompt?

Which of the two prompts below is better? What makes a good prompt?

- "Write about impact of AI."
- "As an AI researcher, write a 200-word editorial explaining the cognitive impact of AI to college students, in a persuasive and friendly tone."

### 1.2 Elements of a Good Prompt — the PARTS Framework

**PARTS:** Persona, Aim, Recipients, Theme, and Structure.

1. **Persona:** identify your role
2. **Aim:** state your objective
3. **Recipients:** specify the audience
4. **Theme:** describe the style, tone, limitation... of the content of the output
5. **Structure:** note the desired format of the output

Applying PARTS, the prompt becomes: *"As an AI researcher, write a 200-word editorial explaining the cognitive impact of AI to college students, in a persuasive and friendly tone."*

### 1.3 Prompt Template with PARTS

```
You are a [Enter your Persona]. [State your Aim and Recipients]. [Describe the content Theme and format Structure].
```

**Example:**

You are a high school history teacher. Design an engaging classroom activity for 11th-grade students focused on creating a collaborative digital timeline about India's struggle for independence. The activity should incorporate storytelling and technology. The time allotment is 3 class periods (60 minutes each). Format this as an outline with bullet points.

*Ref: [5 Building Blocks of a Perfect Prompt (Medium)](https://medium.com/innerscorelearningskillstest/5-building-blocks-of-a-perfect-prompt-5d5e6378d122)*
*Other prompting techniques: [promptingguide.ai/techniques/cot](https://promptingguide.ai/techniques/cot)*

## 2. Create & Vote the Best AI Chatbots

### 2.1 System Prompts

Chatbots can be customized using System Prompts. Apply PARTS to create good system prompts.

Use [Poe](https://poe.com) or [RMIT Val](https://val.rmit.edu.au) to create your own AI chatbot. You can use the following template to create your system prompt:

**Examples:**

- You are a psychological counselor. You talk and help people with mental health issues using **CBT (Cognitive Behavioral Therapy)**. Please converse in a friendly manner, showing care and empathy.
- You are an experienced Python programming instructor. You specialize in helping first-year students learn programming through simple and interesting examples. Please use the following book as the main reference material: [book link].
- You are a Marketing Officer at RMIT Vietnam. Please respond in a friendly and cheerful manner, using emojis. You can speak in both English and Vietnamese. You ONLY answer questions about RMIT, and nothing else. You use information from the knowledge base or from this website: [School website](www.rmit.edu.vn).

### 2.2 Demo System Prompts

- **System Prompt #1:** You are a Marketing Officer at RMIT Vietnam. Please respond in a friendly and cheerful manner, using emojis. You can speak in both English and Vietnamese.
- **System Prompt #2:** You are a Marketing Officer at RMIT Vietnam. Please respond in a friendly and cheerful manner, using emojis. You can speak in both English and Vietnamese. You ONLY answer questions about RMIT, and nothing else.
- **System Prompt #3:** You are a Marketing Officer at RMIT Vietnam. Please respond in a friendly and cheerful manner, using emojis. You can speak in both English and Vietnamese. You ONLY answer questions about RMIT, and nothing else. You only use information from this website: www.rmit.edu.vn.
