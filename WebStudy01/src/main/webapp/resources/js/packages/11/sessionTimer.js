/**
 * 
 */
	$.timeFormat = function(seconds){
		let minute = Math.trunc(seconds / 60);
		let second = seconds % 60;
		return `${new String(minute).padStart(2,'0')}:${new String(second).padStart(2,'0')}`;
	}

	const TimerInfo = function(element, speed){
		this.milliAmount = speed??1000; // null 병합 연산자
		// 생성자 함수가 됨.
		this.timerArea = element;
		// timerArea.data("timeout"); 와 같은의미
		this.timeout = element.dataset.timeout;
		this.extendURL = element.dataset.url;

		this.init = function(){
			// window.timerInfo이 있는지
			if(this.timerArea.timerInfo){
				this.destroy();
			};
			this.timerArea.timerInfo = this;
			this.time = this.timeout;
			this.timer = setInterval(function(){
				if(this.time > 0){
					-- this.time;
					this.timerArea.innerHTML = $.timeFormat(this.time);
				}else{
					this.destroy();	
				}
			// this => this.time
			}.bind(this), this.milliAmount);
			
			if(!this.extendURL) return;
			
			this.msgId = setTimeout(function(){
				let timerObj = this;
				
				Swal.fire({
					  title: '세션을 연장하시겠습니까?',
					  showCancelButton: true,
					  confirmButtonText: 'YES'
					  // 모든 화살표 함수의 주인은 window
					}).then((result) => {
					  /* Read more about isConfirmed, isDenied below */
					  if (result.isConfirmed) {
						  $.ajax({
							  url : timerObj.extendURL,
							  method : "head"
						  });
						  timerObj.init();
					  } 
					})
				// this ->timerInfo	
			}.bind(this), (this.timeout-60)*this.milliAmount);
			
		}
		this.destroy = function(){
			// this.timer => 반복되는 작업이 존재한다면
			if(this.timer){
				clearInterval(this.timer);
			}
			if(this.msgId){
				clearTimeout(this.msgId);
				Swal.close();
			}
			delete this.timerArea.timerInfo;
		}
		this.init();
	}
	
	$("[data-timeout]").each(function(idx, element){
		new TimerInfo(element, 100);
	});
	
	
	
	
	
	
	
