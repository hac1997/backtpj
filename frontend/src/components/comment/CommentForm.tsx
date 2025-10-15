'use client';
import { useState } from "react";
import { CommentService } from "@/services/commentService";

interface CommentFormProps {
  postId: number;
}

export default function CommentForm({ postId }: CommentFormProps) {
  const [body, setBody] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await CommentService.create(postId, {
      body,
      author: "Usuário Exemplo",
      createdAt: new Date().toISOString(),
    });
    setBody("");
  };

  return (
    <form onSubmit={handleSubmit} className="mt-3">
      <textarea
        placeholder="Escreva um comentário..."
        value={body}
        onChange={(e) => setBody(e.target.value)}
        className="w-full border p-2 rounded mb-2"
        required
      />
      <button
        type="submit"
        className="bg-gray-800 text-white px-4 py-2 rounded-lg hover:bg-gray-900"
      >
        Comentar
      </button>
    </form>
  );
}
