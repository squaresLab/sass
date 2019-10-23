public class Plan1571768875230 extends Plan { 
public static void main(String[] args) { 
DecreaseDimmer("C");
StartServer("C");

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}


for (int i = 0; i < 6 ; i++) {
if ( StartServer("A") ) {
StartServer("C");
} else {
DecreaseTraffic("A");
}

}


}
}
