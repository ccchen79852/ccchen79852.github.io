const progress = document.querySelector('.scroll-line');
addEventListener('scroll', () => { const max = document.documentElement.scrollHeight - innerHeight; progress.style.width = `${max ? scrollY / max * 100 : 0}%`; });
const theme = document.querySelector('.theme-toggle');
theme.addEventListener('click', () => { document.body.classList.toggle('dark'); theme.textContent = document.body.classList.contains('dark') ? '☼' : '◐'; });
const cards = document.querySelectorAll('.post-card');
document.querySelectorAll('.filters button').forEach(button => button.addEventListener('click', () => { document.querySelector('.filters .selected').classList.remove('selected'); button.classList.add('selected'); cards.forEach(card => card.hidden = button.dataset.filter !== 'all' && card.dataset.category !== button.dataset.filter); }));
document.querySelector('.newsletter form').addEventListener('submit', event => { event.preventDefault(); event.currentTarget.reset(); document.querySelector('.form-message').textContent = '订阅成功，下一封信很快就来。'; });
