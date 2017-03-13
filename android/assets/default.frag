//#ifdef позвол€ет коду работать на слабых телефонах, и мощных пк.≈сли шейдер используетс€ на телефоне(GL_ES) то  
//используетс€ низка€ разр€дность (точность) данных.(highp Ц высока€ точность; mediump Ц средн€€ точность; lowp Ц низка€ точность)
#ifdef GL_ES   
    #define LOWP lowp
    precision mediump float;
#else
    #define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;
// sampler2D это специальный формат данных в  glsl дл€ доступа к текстуре
uniform sampler2D u_texture;
uniform sampler2D u_texture2;

uniform LOWP float x;
uniform LOWP float y;
uniform LOWP float dst;

uniform LOWP float uTime;
uniform LOWP float zoom;
void main(){

	x=abs(x-gl_FragCoord.x);
	y=abs(y-gl_FragCoord.y);
	dst=(pow((x*x)+(y*y),0.5)/400)-0.5;
	dst*=zoom;
	dst=clamp(dst,0,1);
	
	
	//dst=pow(dst,2);
	
	gl_FragColor.rgb=texture2D(u_texture, v_texCoords)/13;
	
	gl_FragColor.rgb+=texture2D(u_texture, v_texCoords+vec2(0.01*dst,0.01*dst))/13;
	gl_FragColor.rgb+=texture2D(u_texture, v_texCoords+vec2(-0.01*dst,-0.01*dst))/13;
	
	gl_FragColor.rgb+=texture2D(u_texture, v_texCoords+vec2(-0.01*dst,0.01*dst))/13;
	gl_FragColor.rgb+=texture2D(u_texture, v_texCoords+vec2(0.01*dst,-0.01*dst))/13;
	
	
	
	gl_FragColor.rgb+=texture2D(u_texture, v_texCoords+vec2(0.005*dst,0.005*dst))/13;
	gl_FragColor.rgb+=texture2D(u_texture, v_texCoords+vec2(-0.005*dst,-0.005*dst))/13;
	
	gl_FragColor.rgb+=texture2D(u_texture, v_texCoords+vec2(-0.005*dst,0.005*dst))/13;
	gl_FragColor.rgb+=texture2D(u_texture, v_texCoords+vec2(0.005*dst,-0.005*dst))/13;
	
	
	
	gl_FragColor.rgb+=texture2D(u_texture, v_texCoords+vec2(0.0025*dst,0.0025*dst))/13;
	gl_FragColor.rgb+=texture2D(u_texture, v_texCoords+vec2(-0.0025*dst,-0.0025*dst))/13;
	
	gl_FragColor.rgb+=texture2D(u_texture, v_texCoords+vec2(-0.0025*dst,0.0025*dst))/13;
	gl_FragColor.rgb+=texture2D(u_texture, v_texCoords+vec2(0.0025*dst,-0.0025*dst))/13;
	//gl_FragColor.rgb+=(texture2D(u_texture, v_texCoords).r+texture2D(u_texture, v_texCoords).g+texture2D(u_texture, v_texCoords).b)/6*(dst);
	
	//gl_FragColor.rgb+=(texture2D(u_texture, v_texCoords).r+texture2D(u_texture, v_texCoords).g+texture2D(u_texture, v_texCoords).b)/6*(dst);
	gl_FragColor.a=texture2D(u_texture, v_texCoords).a;
	
	/*
	gl_FragColor.r+=0.5*(1-dst);
	gl_FragColor.g+=0.4*(1-dst);
	gl_FragColor.b+=0.3*(1-dst);
	*/
	//gl_FragColor+=texture2D(u_texture, v_texCoords)/dst;
	//gl_FragColor.rgb+=((texture2D(u_texture, v_texCoords)+(1-texture2D(u_texture, v_texCoords+vec2(-0.005/zoom*dst, -0.005/zoom*dst))))/2-0.5)*3;// итоговый цвет пиксел€
	//gl_FragColor.rgb+=((texture2D(u_texture, v_texCoords)+(1-texture2D(u_texture, v_texCoords+vec2(0.005/zoom*dst, 0.005/zoom*dst))))/2-0.5)*3;// итоговый цвет пиксел€
	
	//gl_FragColor.rgb+=((texture2D(u_texture, v_texCoords)+(1-texture2D(u_texture, v_texCoords+vec2(0.001/zoom, -0.001/zoom))))/2-0.5)*(1-dst)*2;// итоговый цвет пиксел€
	//gl_FragColor.rgb+=((texture2D(u_texture, v_texCoords)+(1-texture2D(u_texture, v_texCoords+vec2(0.001/zoom, -0.001/zoom))))/2-0.5)*(1-dst)*2;// итоговый цвет пиксел€
	//gl_FragColor-=((texture2D(u_texture, v_texCoords)+(1-texture2D(u_texture, v_texCoords+vec2(0.001/zoom, 0.001/zoom))))/2-0.5)*10;// итоговый цвет пиксел€
	
	
	//zoom=sin((gl_FragCoord.y+uTime)/1)+sin((gl_FragCoord.y+uTime)/3)+sin((gl_FragCoord.y+uTime)/11);
	//zoom=abs(zoom);
	//zoom=zoom/(zoom+0.005);
	//gl_FragColor+=(1-zoom)*0.2;
	//gl_FragColor.r=v_color * texture2D(u_texture, v_texCoords+vec2(0.02, 0.0));// итоговый цвет пиксел€
	//gl_FragColor.g+=v_color * texture2D(u_texture, v_texCoords+vec2(0.00, 0.02));// итоговый цвет пиксел€
	
		//gl_FragColor.b+=v_color * texture2D(u_texture, v_texCoords+vec2(-0.02, 0.0));// итоговый цвет пиксел€
	//gl_FragColor+=v_color * texture2D(u_texture, v_texCoords+vec2(0.00, -0.02))/10;// итоговый цвет пиксел€
	//gl_FragColor.rgb-=sin(gl_FragCoord.y*0.2*zoom+uTime)/50-0.01; 
	
}