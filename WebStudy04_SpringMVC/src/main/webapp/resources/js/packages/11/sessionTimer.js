/**
 * 
 */
	$.timeFormat = function(seconds){
		let minute = Math.trunc(seconds / 60);
		let second = seconds % 60;
		return `${new String(minute).padStart(2, '0')}:${new String(second).padStart(2, '0')}`;
	}
	const TimerInfo = function(element, speed){
		this.milliAmount = speed??1000;
		this.timerArea = element;
		this.timeout = element.dataset.timeout;
		this.extendURL = element.dataset.url;
		this.init=function(){
			if(this.timerArea.timerInfo){
				this.destroy();
			}
			this.timerArea.timerInfo = this;
			this.time = this.timeout;
			this.timer = setInterval(function(){
				if(this.time > 0){
					-- this.time;
					this.timerArea.innerHTML = $.timeFormat(this.time);
				}else{
					this.destroy();
				}
			}.bind(this), this.milliAmount);
			
			if(!this.extendURL) return;
			
			this.msgId = setTimeout(function(){
				let timerObj = this;
				Swal.fire({
					  title: '세션 연장할텨?',
					  showCancelButton: true,
					  confirmButtonText: 'YES'
					}).then((result) => {
					  /* Read more about isConfirmed, isDenied below */
					  if (result.isConfirmed) {
						$.ajax({
							url:timerObj.extendURL,
							method:"head"
						});
					    timerObj.init()
					  }
					})
			}.bind(this), (this.timeout-60)*this.milliAmount);
		}
		
		this.destroy = function(){
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
	
	
	
	
	
	
	