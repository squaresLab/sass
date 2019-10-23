public class Plan1571768895949 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

StartServer("B");
StartServer("A");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {

}

}

for (int i = 0; i < 2 ; i++) {
StartServer("A");
}





if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
StartServer("B");

}

StartServer("A");


}
}
