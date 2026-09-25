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

## 3. Prompt Engineering for Personas (RMIT Val)

### 3.1 Background Information — Key Concepts

Before crafting prompts for "Personas" in Val, it is useful to distinguish the different prompt elements that exist in the background of a normal Val conversation:

- **System prompt** — may be short or very long, with strong direction and a specific workflow.
- **User prompt** — the user's message or input to the AI model.
- **Assistant prompt** — the AI model's response; multiple assistant messages may be supplied simultaneously (e.g., two supporting documents and the AI model's response).

**Example conversation:**

```json
{
  "messages": [
    {
      "role": "system",
      "content": "You are an AI language model assisting with various queries."
    },
    {
      "role": "user",
      "content": "What's the weather like today?"
    },
    {
      "role": "assistant",
      "content": "{ 'melbourne': 25, 'sydney': 28, 'adelaide': 31 }"
    },
    {
      "role": "assistant",
      "content": "Today's weather is sunny with a high of 25°C."
    },
    {
      "role": "user",
      "content": "Great, thanks! Can you also help me with a math problem?"
    },
    {
      "role": "assistant",
      "content": "Of course! What's the math problem you need help with?"
    }
  ]
}
```

### 3.2 Isn't It Just a Conversation?

For many users, interacting in Val feels like a conversation due to the chat-style interface. But when creating a "Persona," you transition from a direct 'user' to a 'developer,' directing Val on how to interact as an "Assistant" in a specific context that you stipulate — pre-determining the 'User' prompt interactions.

To create "Personas," it is helpful to think of prompts in **layers**: System, Context, and Role. These layers can overlap in how they are structured, but each serves a slightly different purpose. A 'System' prompt and how it is written will also tend to incorporate 'Context' and 'Role.' The 'System' layer is the foundational element of your prompt, from which you develop increasing specificity to help tailor the interaction between your user and the model.

When a user selects that "Persona," the conversation they have will be informed by your system prompt. You can establish guardrails on behaviours and set parameters for the types of interaction and outputs.

Your "Persona" will have contextual information and role specifics that the user does not see — therefore, as educators, you can create a highly personalised learning experience.

### 3.3 Layers: System, Context & Role

| Layer | Prompt Technique | Description | Example |
| --- | --- | --- | --- |
| Bottom | System | Set the overall framework or parameters in which the model(s) operate and intended purpose. | Provide phonetically structured translations from English to French that are polite. Refer to **[[Knowledge]]** only as a resource for translations. The user will provide a situation or phrase that requires appropriate translation. When responding, also include an English explainer. |
| Middle | Context | Provides specific details or information required to complete the task. | You are translating from English to French for a tourist travelling across France. Provide phonetically structured translations that are polite in response to the users' requests. |
| Top | Role | Assigns a consistent specific role or identity to the model that informs its behaviour as it responds to the user. | I want you to act as a friendly personal translator. The user will ask you what to say in French based on a situation, and you should provide three different options they can respond with. The options must include phonetics and an English explainer. |

### 3.4 Creating a Persona Prompt Template

A prompt template helps structure your 'System'-style prompt to direct Val's behaviour as an "Assistant" and its response to anticipated User prompt inputs. To create, open, and edit a persona template, use **Visual Studio Code**, which can be installed from the RMIT Company Portal.

The template uses **Markdown** formatting. Markdown helps Val interpret your prompt — it provides structure and increases the clarity of your instructions, and providing this document format (`.md`) to the Val team helps them configure it. Markdown formatting creates both hierarchy and structure; it helps direct the model's "attention" to the most important elements of your prompt, increasing the potential quality and consistency of behaviour.

*Reference: [Markdown Cheat Sheet](https://www.markdownguide.org/cheat-sheet/)*

### 3.5 Detailed System Prompt Template — Example

The following is an example "Persona" prompt. It is **not** a real Val Persona and is purely illustrative — it is also not an exemplar of best practice.

There is no single "best practice" prompt — it is about exploring formats, testing, iterating, and adapting based on different models. As Val integrates more models and functionality, "Personas" and other prompts will likely require different structures or inclusions, so experiment! There are many guides available online from model developers (e.g., Google, OpenAI) on how to craft or format prompts specific to their models, and many users/experts debate methods for prompting or discover new approaches.

The example below has been tested and works, but it is likely that a different markdown structure would still work as anticipated. Feel free to play with it!

#### Example — "TRANSLO" System Prompt (Part 1)

| Prompt | Explanation |
| --- | --- |
| You are named TRANSLO. | It is important to use a name for the 'Assistant' that helps provide the foundation as a system prompt. It creates an identity. |
| `#Purpose`<br>You are a translator for an English speaking tourist travelling across Europe. You provide polite translations based on the user requests using only established translations that do not include slang. | A first-level heading indicated by `#` provides the Purpose for the 'Assistant' when responding to User prompts. This includes some general background information. |
| `#Context`<br>`##Variables`<br>`{language}` = OPTIONS `{French\|Italian\|German}`<br>`{translation type}` = OPTIONS `{situation\|phrase}`<br>`{situation}` = a specific context that could involve different interactions — example: *I am in a restaurant and want to order a meal*<br>`{phrase}` = a group of two or more words in a sentence — example: *I would like a cheeseburger* | A first-level heading `#` then introduces information relating to Context, with a subheading `##` of Variables. These variables are not real coding variables in the model data — they are written as simple variables within the prompt itself to allow easier substitutions and reduce repetitions. The curly brackets `{}` provide the placeholder text that then gets used throughout the prompt, and the `=` allows indication of the intended variable(s) and an example (one-shot). |
| `##Conditions`<br>If the user requests `{translation type}` = `{situation}` you should provide three translation options for them that are appropriate.<br>If the user requests `{translation type}` = `{phrase}` you should provide a direct translation as appropriate. | Another second-level heading sets some additional conditions relating to the variables. |
| `#Output`<br>`##Response format`<br>Your response should follow the format of:<br>**Translation:**<br>**Phonetic Pronunciation:**<br>**Explanation:** | A first-level heading `#` introduces information about outputs followed by a second-level heading about the Response format, indicating `** **` bold text. |

### 3.6 Detailed System Prompt — Example (Continued, Part 2)

| Prompt | Explanation |
| --- | --- |
| `##Constraints`<br>Once the user has chosen a `{language}` continue to provide translations based on that choice until told otherwise.<br>When providing an explanation ensure it always includes a direct English translation in addition to any other details that seem appropriate. | A second-level heading indicated by `##` advises the 'Assistant' of constraints. These provide guardrails for its outputs. It is beneficial to write constraints in affirmative language. These can be provided as a list, statements, or even include examples. |
| `#Interaction`<br>Follow these steps in order:<br>1. Introduce yourself as TRANSLO and let the user know that you can provide translation assistance to support their travels across Europe.<br>2. Ask the user what `{language}` (OPTIONS: French, Italian, German) they need help with.<br>3. Wait for the user to select the `{language}` before continuing.<br>4. Now ask the user what `{translation type}` (OPTIONS: situation, phrase) they require. Explain the difference between `{situation}` and `{phrase}` to help them choose effectively.<br>5. Wait for the user to select `{translation type}` before continuing.<br>6. Provide a translation in the response format according to the conditions chosen.<br>7. If the user requested `{translation type}` = `{situation}`, then ask if they need more translation options. If the user indicates they need more options, provide three additional translation options that are appropriate.<br>8. Advise the user you are ready to assist them again. Ask whether they need help with a `{translation type}` (situation or phrase). | A first-level heading finally introduces `#Interaction`. This might also be referred to as a workflow for the 'Assistant.' It essentially guides the sequence of inputs and outputs expected between the 'Assistant' and User prompts to facilitate a conversation. |
