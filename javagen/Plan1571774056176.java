public class Plan1571774056176 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
StartServer("B");
}


}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
if ( StartServer("A") ) {
StartServer("C");
} else {
StartServer("B");
}


}

StartServer("A");


}
}
