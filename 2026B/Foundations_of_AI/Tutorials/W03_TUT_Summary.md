# WEEK 3 TUTORIAL — Review Summary

## 🗂️ Outline
1. How to Write Good Prompts for AI Chatbots
2. Create & Vote the Best AI Chatbots

---

## 1. How to Write Good Prompts for AI Chatbots

### Brainstorm (warm-up)
Work in pairs and compare the two prompts below — which one is better? Why?
- **Prompt A:** *"As an AI researcher, write a 200-word editorial explaining the cognitive impact of AI to college students, in a persuasive and friendly tone."*
- **Prompt B:** *"Write about impact of AI."*

→ Share your own prompting experience: what makes a good prompt?

### 🔑 Elements of a Good Prompt — the **PARTS** Framework
| Letter | Meaning | Content |
|---|---|---|
| **P** | Persona | Identify your role |
| **A** | Aim | State your objective |
| **R** | Recipients | Specify the audience |
| **T** | Theme | Describe the style, tone, limitations of the output |
| **S** | Structure | Note the desired format of the output |

**References:**
- medium.com/innerscorelearningskillstest/5-building-blocks-of-a-perfect-prompt-5d5e6378d122
- Other prompting techniques: promptingguide.ai/techniques/cot (Chain-of-Thought)

**Applying PARTS to the example:** *"As an AI researcher, write a 200-word editorial explaining the cognitive impact of AI to college students, in a persuasive and friendly tone."*
→ Persona: AI researcher | Aim: write a 200-word editorial | Recipients: college students | Theme: persuasive & friendly | Structure: 200-word editorial

### Prompt Template with PARTS
> "You are a **[Persona]**. **[State your Aim and Recipients]**. **[Describe the content Theme and format Structure]**."

**Worked example:**
> *"You are a high school history teacher. Design an engaging classroom activity for 11th-grade students focused on creating a collaborative digital timeline about India's struggle for independence. The activity should incorporate storytelling and technology. The time allotment is 3–4 class periods (60 minutes each). Format this as an outline with bullet points."*

---

## 2. Create Your Own Custom Chatbots

### System Prompts
- Chatbots can be customized using a **System Prompt**.
- Apply the **PARTS** framework to write good system prompts.

**Sample System Prompts:**
1. *"You are a psychological counselor. You talk and help people with mental health issues using CBT (Cognitive Behavioral Therapy). Please converse in a friendly manner, showing care and empathy."*
2. *"You are an experienced Python programming instructor. You specialize in helping first-year students learn programming through simple and interesting examples. Please use the following book as the main reference material: [book link]."*
3. *"You are a Marketing Officer at RMIT Vietnam. Please respond in a friendly and cheerful manner, using emojis. You can speak in both English and Vietnamese. You ONLY answer questions about RMIT, and nothing else. You use information from the knowledge base or from this website: www.rmit.edu.vn."*

### Instructions for **Poe**
1. Sign up / Log in at poe.com
2. Click "Create"
3. Select a bot style (choose **"Prompt bot"** for this demo)
4. Fill in the bot info (most important: the **"Prompt"** field)
5. Publish & Share

### Instructions for **RMIT Val**
1. Log in at val.rmit.edu.au
2. Click "My Studio" → "Create Persona"
3. Fill in the bot info (most important: the **"System Prompt"** field — see the detailed guide in "RMIT Val System Prompt")
4. Save & Create

### Demo — Comparing System Prompts with Increasing Constraints
| # | Content |
|---|---|
| 1 | Marketing Officer at RMIT Vietnam, friendly, uses emojis, bilingual English–Vietnamese |
| 2 | Same as #1 + **ONLY** answers questions about RMIT |
| 3 | Same as #2 + **only uses information from** www.rmit.edu.vn |

### Demo Test Prompts (sample questions to test the chatbot)
- Hi
- How many campuses does RMIT Vietnam have?
- What is the address of Saigon campus?
- What is the weather like today?
- Can we meet at 7AM?
- Give me phone number

> 💡 Purpose: check whether the chatbot **correctly respects the constraints** set in its system prompt (e.g., does it answer off-topic questions like "what's the weather today" when it shouldn't?).

### Demo with **Hugging Chat**
- Sign up / Log in at huggingface.co/chat
- Edit the system prompt
- Try the chatbots with the same 3 system prompts as in the Poe/RMIT Val demos

---

## Main Class Activity
1. **[2 mins]** Tutor divides the class into 3–4 groups.
2. **[15 mins]** Create and try your team's chatbot (using Poe, RMIT Val, or other platforms such as huggingface.co/chat, chatbase.co, voiceflow.com).
3. **[2 mins]** Submit your team's system prompt + test-prompt demo (screenshots if possible) to: `tiny.cc/FOAI-TUT3`
4. **[3 mins per group]** Showcase your group's chatbot.
5. **[2 mins]** Vote for the best/most interesting chatbot at: `menti.com/alxmx92f3gef`

---

## ✅ Key takeaways for review
- Memorize the **PARTS** framework (Persona – Aim – Recipients – Theme – Structure) and know how to apply it to the prompt template.
- Understand what a **System Prompt** is and its role in customizing a chatbot (limiting the scope of answers, communication style, information sources).
- Know the basic steps to create a chatbot on Poe and RMIT Val.
- Understand why **test prompts** matter (to check whether the chatbot respects the constraints it was given).
