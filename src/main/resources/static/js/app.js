function getCart(){return Number(localStorage.getItem('marketly-cart')||0)}
function setCart(v){v=Math.max(0,v);localStorage.setItem('marketly-cart',v);document.querySelectorAll('#cartCount').forEach(e=>e.textContent=v)}
function toast(message){const t=document.querySelector('#toast');if(!t)return;t.textContent=message;t.classList.add('show');setTimeout(()=>t.classList.remove('show'),1800)}
document.querySelectorAll('.add').forEach(btn=>btn.addEventListener('click',()=>{setCart(getCart()+1);toast((btn.dataset.name||'Item')+' added to cart');btn.innerHTML='Added ✓';setTimeout(()=>btn.innerHTML='Add to cart <span>+</span>',1200)}))
document.querySelectorAll('#cartCount').forEach(e=>e.textContent=getCart())
const sort=document.querySelector('#sortProducts'), grid=document.querySelector('#productGrid')
if(sort&&grid)sort.addEventListener('change',()=>{const items=[...grid.children];if(sort.value==='low')items.sort((a,b)=>Number(a.dataset.price)-Number(b.dataset.price));if(sort.value==='high')items.sort((a,b)=>Number(b.dataset.price)-Number(a.dataset.price));items.forEach(i=>grid.appendChild(i))})
const menu=document.querySelector('.menu-toggle');if(menu)menu.addEventListener('click',()=>document.querySelector('.main-nav')?.classList.toggle('open'))
