public class Plan1571768419016 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
DecreaseDimmer("B");

}

StartServer("C");

for (int i = 0; i < 2 ; i++) {
if ( StartServer("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


StartServer("A");

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}

}


}
}
