package rs.spaceinvaders;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Color;
import android.view.MotionEvent;
import android.os.Handler;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class MainActivity extends Activity {
	ViewSpaceInvaders vsi;
	float maxx,maxy;
	Bitmap splash,logo,logotexto,fundo,coracaodir,coracaoesq,nave[]=new Bitmap[3],ovni[]=new Bitmap[3],tiro[]=new Bitmap[3];
	boolean modojogo;
	int recorde,vida=4,pontos,posicao=5,tiponave,tirox[]=new int[20],tiroy[]=new int[20],ovnix[]=new int[3],ovniy[]=new int[3],ovnitipo[]=new int[3];
	final Handler meuhandler=new Handler();
	Timer meutimer=new Timer();
	SharedPreferences valorp;
	@Override public void onBackPressed(){
		if(!modojogo)super.onBackPressed();
		else{
			modojogo=false;
			vsi.invalidate();
		}
	}
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vsi=new ViewSpaceInvaders(this);
		Point size=new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		maxx=Math.max(size.x,size.y);//800
		maxy=Math.min(size.x,size.y);//480
		valorp=PreferenceManager.getDefaultSharedPreferences(this);
		if(valorp.contains("recorde"))recorde=valorp.getInt("recorde",0);
		logo=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.iconotitulo),(int)(maxx/4),(int)(maxx/4),true);
		logotexto=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.iconotitulo1),(int)(maxx/4),(int)(maxx/4),true);
		splash=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fondo2),(int)maxy,(int)maxx,true);
		fundo=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fondo3),(int)maxy,(int)maxx,true);
		nave[0]=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.diseno11),(int)maxy/10,(int)maxx/10,true);
		nave[1]=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.diseno21),(int)maxy/10,(int)maxx/10,true);
		nave[2]=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.diseno31),(int)maxy/10,(int)maxx/10,true);
		ovni[0]=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.zobjectasteroie),(int)maxy/10,(int)maxx/10,true);
		ovni[1]=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.enemigodiseno11),(int)maxy/10,(int)maxx/10,true);
		ovni[2]=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.enemigodiseno21),(int)maxy/10,(int)maxx/10,true);
		tiro[0]=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.municion),(int)maxy/20,(int)maxx/20,true);
		tiro[1]=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.municion1),(int)maxy/20,(int)maxx/20,true);
		tiro[2]=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.municion2),(int)maxy/20,(int)maxx/20,true);
		coracaodir=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.vcorazonder),(int)maxy/20,(int)maxx/20,true);
		coracaoesq=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.vcorazonizq),(int)maxy/20,(int)maxx/20,true);
		setContentView(vsi);
		meutimer.schedule(new TimerTask(){
			public void run(){
				if(modojogo){
					int x;
					for(x=0;x<3;x++)if(ovnix[x]>20){
						if(ovnitipo[x]!=0)vida--;
						geraovnis(x);
					}
					for(x=0;x<3;x++){
						ovnix[x]++;
						if(ovnix[x]==19&&ovniy[x]==posicao)modojogo=false;
					}
					int y;
					for(y=0;y<20;y++)if(tirox[y]>=0)tirox[y]--;
					for(x=0;x<3;x++){
						for(y=0;y<20;y++)if(tirox[y]!=-1&&tiroy[y]==ovniy[x]&&(tirox[y]==ovnix[x]||tirox[y]==ovnix[x]-1)){
							if(ovnitipo[x]!=0){
								pontos++;
								geraovnis(x);
								if(vida<4)vida++;
							}
							tirox[y]=-1;
						}
					}
					if(vida==0)modojogo=false;
					if(pontos>recorde){
						Editor editor=valorp.edit();
						editor.putInt("recorde",pontos);
						editor.commit();
						recorde=pontos;
					}
					meuhandler.post(meurunnable);
				}
			}
		},0,100);
	}
	void geraovnis(int n){
		do{
			ovnix[n]=-new Random().nextInt(12);
		}while(ovnix[n]==ovnix[((n+1)%3)]||ovnix[n]==ovnix[((n+2)%3)]);
		ovniy[n]=new Random().nextInt(11);
		ovnitipo[n]=new Random().nextInt(3);
	}
	final Runnable meurunnable=new Runnable(){public void run(){vsi.invalidate();}};
	class ViewSpaceInvaders extends View{
		public ViewSpaceInvaders(Context c){super(c);}
		@Override public boolean onTouchEvent(MotionEvent event){
			if(!modojogo){
				pontos=0;
				vida=4;
				int x;
				for(x=0;x<3;x++)geraovnis(x);
				for(x=0;x<20;x++)tirox[x]=-1;
				tiponave=new Random().nextInt(3);
				modojogo=true;
			}
			float x=event.getX(),y=event.getY();
			if(event.getAction()==MotionEvent.ACTION_DOWN){
				if(y>maxx*.7){
					if(x>maxy/2)posicao=(posicao+1)%11;
					else posicao=(posicao+10)%11;
				}
				else{
					int n=0;
					while(tirox[n]!=-1)n++;
					tirox[n]=19;
					tiroy[n]=posicao;
				}
			}
			invalidate();
			return super.onTouchEvent(event);
		}
		@Override protected void onDraw(Canvas c){
			super.onDraw(c);
			Paint p=new Paint();
			p.setColor(Color.GREEN);
			p.setTextSize((float)(maxy*0.04));
			if(modojogo){
				c.drawBitmap(fundo,0,0,p);
				c.drawBitmap(nave[tiponave],(int)((maxy/11)*posicao),(int)(maxx*.88),p);
				c.drawText(String.valueOf(pontos*100),10,30,p);
				c.drawLine(0,(int)(maxx*.7),maxy,(int)(maxx*.7),p);
				int x;
				for(x=0;x<3;x++)c.drawBitmap(ovni[ovnitipo[x]],(int)((maxy/11)*ovniy[x]),(int)(ovnix[x]*(maxx/20)),p);
				for(x=0;x<20;x++)if(tirox[x]>=0)c.drawBitmap(tiro[tiponave],(int)((maxy/11)*tiroy[x])+(maxy/33),(int)(tirox[x]*(maxx/20)),p);
				c.drawBitmap(coracaodir,(int)(maxy*.95)-20,20,p);
				if(vida>1)c.drawBitmap(coracaoesq,(int)(maxy*.90)-20,20,p);
				if(vida>2)c.drawBitmap(coracaodir,(int)(maxy*.85)-40,20,p);
				if(vida>3)c.drawBitmap(coracaoesq,(int)(maxy*.80)-40,20,p);
			}
			else{
				c.drawBitmap(splash,0,0,p);
				c.drawBitmap(logo,-(logo.getWidth())/3,-(logo.getHeight())/3,p);
				c.drawBitmap(logotexto,(maxy-logotexto.getWidth())/2,(maxx-logotexto.getHeight())/3,p);
				if(recorde>3)c.drawText(String.valueOf(recorde*100),maxy-60,32,p);
			}
		}
	}
}
