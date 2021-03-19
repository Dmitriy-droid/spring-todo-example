"use strict";

const list = document.querySelector(".todo-list");
const addTaskInput = document.querySelector(".add-task__input");
const addTaskBtn = document.querySelector(".add-task__btn");

class App {
  async loadTasks() {
    const res = await fetch("/tasks");
    const json = await res.json();
    this.renderTasks(json);
  }

  renderTasks(tasks) {
    list.innerHTML = "";
    for (const task of tasks) {
      const taskEl = this.createTaskEl(task);
      list.appendChild(taskEl);
    }
  }

  mkDiv(className, text) {
    const el = document.createElement("div");
    el.classList.add(className);
    el.textContent = text ?? "";
    return el;
  }

  createTaskEl(task) {
    const el = document.createElement("div");
    el.classList.add("task", task.completed && "task--completed");

    const checkbox = document.createElement("input");
    checkbox.classList.add("task__checkbox");
    checkbox.type = "checkbox";
    checkbox.checked = task.completed;
    checkbox.addEventListener("click", async () => {
      await this.toggle(task);
      await this.loadTasks();
    });
    el.appendChild(checkbox);

    el.appendChild(this.mkDiv("task__id", "#" + task.id));
    el.appendChild(this.mkDiv("task__name", task.name));

    const del = document.createElement("a");
    del.classList.add("task__del");
    del.href = "javascript:void(0)";
    del.textContent = "удалить";
    del.addEventListener("click", async e => {
      e.preventDefault();
      await this.remove(task);
      await this.loadTasks();
    });
    el.appendChild(del);
    return el;
  }

  async toggle(task) {
    const data = new FormData();
    data.append("id", task.id);
    data.append("value", !task.completed);
    const res = await fetch("/tasks/complete", { method: "POST", body: data });
    return res.json();
  }

  async remove(task) {
    const data = new FormData();
    data.append("id", task.id);
    const res = await fetch("/tasks", { method: "DELETE", body: data });
    return res.json();
  }

  addHandlers() {
    const send = async () => {
      const text = addTaskInput.value;
      if (!text) {
        alert("Напишите текст задачи!");
        return;
      }
      addTaskInput.value = "";
      addTaskInput.focus();
      await this.doAddTask(text);
      await this.loadTasks();
    };
    addTaskBtn.addEventListener("click", send);
    addTaskInput.addEventListener("keydown", async e => {
      if (e.key === "Enter") {
        await send();
      }
    });
  }

  async doAddTask(text) {
    const data = new FormData();
    data.append("text", text);
    const res = await fetch("/tasks", { method: "POST", body: data });
    return res.json();
  }

  run() {
    this.loadTasks().then(() => {
      this.addHandlers();
    });
  }
}

new App().run();
