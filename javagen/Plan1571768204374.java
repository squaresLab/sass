public class Plan1571768204374 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

DecreaseTraffic("A");

} else {
StartServer("A");
}


DecreaseDimmer("B");

for (int i = 0; i < 4 ; i++) {
DecreaseTraffic("A");
}


StartServer("C");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



}
}
