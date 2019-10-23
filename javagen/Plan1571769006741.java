public class Plan1571769006741 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



if ( DecreaseDimmer("C") ) {
for (int i = 0; i < 3 ; i++) {
DecreaseDimmer("A");
}

} else {
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("B");
}

}

}


}
}
