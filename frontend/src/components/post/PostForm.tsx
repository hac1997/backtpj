'use client';
import { useState } from "react";
import { PostService } from "@/services/postService";

export default function PostForm() {
  const [title, setTitle] = useState("");
  const [body, setBody] = useState("");
  const [tags, setTags] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await PostService.create({
      title,
      body,
      tags: tags.split(",").map(t => t.trim()),
      author: "Usuário Exemplo",
      createdAt: new Date().toISOString(),
    });
    setTitle("");
    setBody("");
    setTags("");
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="bg-white shadow p-6 rounded-2xl mb-6"
    >
      <h2 className="text-lg font-semibold mb-4">Criar novo post</h2>
      <input
        type="text"
        placeholder="Título"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        className="w-full border p-2 rounded mb-3"
        required
      />
      <textarea
        placeholder="Escreva seu post..."
        value={body}
        onChange={(e) => setBody(e.target.value)}
        className="w-full border p-2 rounded mb-3 h-32"
        required
      />
      <input
        type="text"
        placeholder="Tags (separadas por vírgula)"
        value={tags}
        onChange={(e) => setTags(e.target.value)}
        className="w-full border p-2 rounded mb-3"
      />
      <button
        type="submit"
        className="bg-blue-600 text-white py-2 px-4 rounded-lg hover:bg-blue-700 transition"
      >
        Publicar
      </button>
    </form>
  );
}
