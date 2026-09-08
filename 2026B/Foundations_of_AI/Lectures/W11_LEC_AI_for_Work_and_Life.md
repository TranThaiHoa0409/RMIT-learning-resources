# COSC2968/COSC3053 – Foundations of Artificial Intelligence
## Week 11: AI for Work & Life

**This week's topic (last lecture):** AI for Work & Life — a practical tour of AI tools across multimedia, no-code deep learning, automation, and agents.

---

## 1. Multimedia + AI

### Image Tools
- **Image Upscaling:** upscale.media, imgupscaler.com
- **Object Removing:** cleanup.pictures, Adobe Firefly (brush-based); or prompt-based via chatgpt.com / gemini.google.com/app (Gemini noted as "better, faster" for this)
- **[Bonus] Color Changing & More:** aiease.ai/app/ai-recolor
- **Image Generation:** gemini.google.com ("Nano Banana"), chatgpt.com, piclumen.com/ai-image, canva.com/ai-image-generator
  - Good prompts describe subject + style/effect in detail (e.g., "macro photography, close-up, hyper detailed, studio photo, intricate details" — ref: prompthero.com)
  - Also demoed via Adobe Photoshop's built-in image generation function

### Music & Video Tools
- **Music Generation:** suno.com, udio.com, elevenlabs.io, aiva.ai
- **Video Generation:** ai.byteplus.com/lumina, Adobe Firefly (AI video generator), Gemini video generation, CapCut

---

## 2. Build Deep Learning Models (No-Code)

### Teachable Machine
- **Tool:** teachablemachine.withgoogle.com — a no-code tool for training DL models directly in the browser.
- **Demo pattern:** train an **image classification** model by giving example classes (e.g., classify "Me" vs. "Bottle"; extend to 3 classes by adding "Phone").
- **[Bonus]** Same platform also supports **audio/voice-command classification** (e.g., recognizing "Open door" vs. "Open windows").
- **Takeaway:** shows that training a classifier is fundamentally about **collecting labeled examples per class**, regardless of whether the input is image or audio — the no-code tool handles feature extraction and model training automatically.

---

## 3. Task Automation

### No-Code Automation Platforms
- **Tools:** zapier.com, n8n.io, diaflow.io
- **Core concept — a "Zap" (or automation workflow):** connects a **trigger** (e.g., a new Facebook comment) to one or more **actions** (e.g., logging it to a Google Sheet), optionally with an **AI step** in between to process/classify the data before the action.

### Worked Examples (Zapier)
1. **Tracking Facebook comments:** Google Sheet with columns to log comments → a Zap that triggers on new comments and writes them to the sheet.
2. **[Bonus] AI-enhanced tracking:** add extra columns to the sheet (e.g., sentiment, category, auto-reply) and insert an **AI action step** in the Zap to generate that extra data automatically.
3. **[Bonus] Chatbot via Zapier:** a chatbot built from a **directive/system prompt** with 3 parts — **Objective** (role + allowed knowledge scope), **Style** (tone), **Other Rules** (language, topic restriction). This mirrors the **PARTS**-style prompt structuring seen in earlier weeks.

---

## 4. Other AI Tools

### RAG Chatbots
- **RAG (Retrieval-Augmented Generation):** a chatbot architecture that answers using a **specific knowledge base** (e.g., your own PDFs, websites) rather than only its general training knowledge.
- **Use cases:** company sales/marketing chatbots, a "personal expert" tailored to a specific field.
- **Tools:** chatbase.co, voiceflow.com
- **Chatbase workflow (demoed):**
  1. Create a new AI agent.
  2. Add **Sources**: a website URL and/or files (e.g., a PDF).
  3. Write a **system prompt** defining persona, tone, language, and a hard constraint: *only answer using the provided sources — do not invent information.*
  4. **Deploy** via a chat-bubble widget (copy the embed script to add to any website).
- **Testing a RAG chatbot:** probe it with both in-scope questions (should answer accurately from the sources) and out-of-scope questions (should correctly decline, per its system prompt).

### Google AI Studio
- **What it is:** a web-based studio (aistudio.google.com) for experimenting with AI models and building simple apps (websites, media, tools) from natural-language prompts — "vibe coding" / prompt-to-app.
- **Demo pattern:** describe an app in a prompt (e.g., "create a simple website about RMIT with an embedded chatbot widget") → the tool generates a working app; iterate by giving follow-up edit prompts (e.g., "add a download button").

### NotebookLM
- **What it is:** a tool (notebooklm.google.com) for researching and "chatting" with your own uploaded documents — grounds its answers in the specific sources you provide (a RAG-style, document-centric use case).

### Running AI Models Locally
| | |
|---|---|
| **Pros** | **Security** (reduces data-breach/unauthorized-access risk); **Cost efficiency** (no API/cloud fees); **Offline access** (works without internet) |
| **Cons** | **Setup complexity** (self-install & self-host); **Maintenance cost** (slower updates, may need IT support) |

- **Tools:** Ollama, LMStudio, AnythingLLM, GPT4All

### AI Coding Tools
- Help programmers write, understand, and debug code faster.
- **Examples:** Claude Code, Google Antigravity, Cursor, GitHub Copilot

### AI Agents
> ⚠️ **Warning (explicitly stated in lecture):** AI agents may expose your systems to **severe security vulnerabilities** and may cause **cognitive decay** by outsourcing critical human thinking.

- **Definition:** autonomous AI agents are platforms designed to execute **complex, multi-step tasks** across your local data and resources with **minimal human intervention**.
- **Examples:** OpenClaw, Hermes Agent, Claude Cowork

---

## 5. Course Wrap-Up

**By the end of this course, students have gained:**
- **Knowledge:** AI development approaches; history & future of AI; core AI concepts; key ethics approaches.
- **Tech skills:** developing ML projects (no-code & coding tools); writing ML project reports.
- **Soft skills:** problem-solving; teamwork & presentation; understanding and applying ethical principles.

---

*Converted from `W11_LEC_AI_for_Work_and_Life.pptx` for personal exam review (Assignment 2, Foundations of AI — COSC2968/COSC3053). Note: per the Assignment 2 brief, Week 11 content is examinable from the **lecture only** (no Week 11 tutorial in scope).*
